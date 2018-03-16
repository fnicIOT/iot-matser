package com.fnic.sysframe.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Component
public class JWTTokenUtil implements Serializable {

    public static String generateToken(Map<String,Object> claims) {

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 15 * 1000))
                //.setExpiration(new Date(System.currentTimeMillis() + 6*1000))
                .signWith(SignatureAlgorithm.HS512, "FnicJWT")
                .compact();
    }

    public static Claims getClaimsFromToken(String token) {
        Claims claims;

        try {
            claims = Jwts.parser()
                    .setSigningKey("FnicJWT")
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();
        }catch (Exception e) {
            claims = null;
        }

        return claims;
    }

    public static boolean isTokenExpired(String token) {

        try{
            Claims claims = getClaimsFromToken(token);
            Date date = claims.getExpiration();
            return date.before(new Date());
        }catch (Exception e) {
            return true;
        }
    }
}
