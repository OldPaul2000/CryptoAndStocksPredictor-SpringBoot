package com.universitymlproject.cryptopredictor.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Service
public class LogoutService implements LogoutHandler {

    private JwtService jwtService;

    @Value("${jwt.secret-key}")
    private String SECRET;

    @Value("${jwt.header}")
    private String AUTHORIZATION_HEADER;

    public LogoutService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String jwt = request.getHeader("Authorization");
        if(jwt != null){
            try{
                SecretKey secretKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
                if(secretKey != null){
                    Claims claims = Jwts.parser().verifyWith(secretKey)
                            .build().parseSignedClaims(jwt).getPayload();
                    long userId = Long.parseLong(String.valueOf(claims.get("userId")));

                    jwtService.invalidateToken(userId, jwt);

                    SecurityContextHolder.clearContext();
                }
            }
            catch (Exception e){
                response.setHeader("cause", "JWT expired");
                SecurityContextHolder.clearContext();
            }
        }
    }
}
