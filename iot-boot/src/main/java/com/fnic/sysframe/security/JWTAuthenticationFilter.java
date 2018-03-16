package com.fnic.sysframe.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fnic.bean.RspBean;
import com.fnic.sysframe.utils.JWTTokenUtil;
import com.fnic.sysframe.utils.UserUtil;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * token的校验
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 * @author zhaoxinguo on 2017/9/13.
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    private final ObjectMapper mapper = new ObjectMapper();

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);

    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            // parse the token.
            if(!JWTTokenUtil.isTokenExpired(token)) {
                Claims claims = JWTTokenUtil.getClaimsFromToken(token);

                SysUser sysUser = new SysUser();

                sysUser.setEmail(claims.get("email").toString());
                //sysUser.setUsername(claims.get("userName").toString());
                sysUser.setTenantId(claims.get("tenantId").toString());
                sysUser.setCustomerId(claims.get("customerId").toString());

                if (sysUser != null) {
                    return new UsernamePasswordAuthenticationToken(sysUser, null, new ArrayList<>());
                }
            }else {
                return null;
            }

            return null;
        }
        return null;
    }

    @Override
    protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {

        SecurityContextHolder.clearContext();

        RspBean rspBean = new RspBean();
        rspBean.setRspDesc("UNAUTHORIZED");
        rspBean.setRspCode("401");

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        mapper.writeValue(response.getWriter(),rspBean);
    }
}
