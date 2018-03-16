package com.fnic.sysframe.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fnic.bean.RspBean;
import com.fnic.sysframe.utils.JWTTokenUtil;
import com.google.common.collect.Maps;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import static com.fnic.sysframe.utils.UserUtil.getUser;

/**
 * Created by hjhuang on 2017/5/17.
 */
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //super.setDefaultTargetUrl("/login/success");
        //super.onAuthenticationSuccess(request, response, authentication);

        SysUser user = getUser();

        Map<String,Object> claims = Maps.newHashMap();

        claims.put("email",user.getEmail());
        claims.put("tenantId",user.getTenantId());
        claims.put("customerId",user.getCustomerId());
        claims.put("userName",user.getUsername());
        String token = JWTTokenUtil.generateToken(claims);

        RspBean rspBean = new RspBean();
        rspBean.setRspDesc("Login Success");
        rspBean.put("token",token);

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        mapper.writeValue(response.getWriter(),rspBean);
    }
}
