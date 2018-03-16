package com.fnic.sysframe.config;

import com.fnic.sysframe.security.CustomAuthenticationSuccessHandler;
import com.fnic.sysframe.security.CustomUserService;
import com.fnic.sysframe.security.CustomUsernamePasswordAuthenticationFilter;
import com.fnic.sysframe.security.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by hjhuang on 2017/2/7.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService customUserService() {
        return  new CustomUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        CustomUsernamePasswordAuthenticationFilter filter = new CustomUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationManager(this.authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler());

        http.authorizeRequests()
                .antMatchers("/","/login","/index","/assets/**","/static/**","/*.js","/*.css","/*.map").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/static/index.json")
                .loginProcessingUrl("/login")//登陆处理路径
                //.successForwardUrl("/login/success")
                //.defaultSuccessUrl("/login/success")//登陆成功路径
                .failureUrl("/error")
                .permitAll()
                .and()
                .logout().permitAll().logoutUrl("/logout").logoutSuccessUrl("/logout/success")
                .and().addFilterAt(filter, UsernamePasswordAuthenticationFilter.class)
                .addFilter(buildJWTAuthenticationFilter());

          http.csrf().disable();
    }

    private JWTAuthenticationFilter buildJWTAuthenticationFilter() throws Exception {
        return new JWTAuthenticationFilter(authenticationManager());
    }
}
