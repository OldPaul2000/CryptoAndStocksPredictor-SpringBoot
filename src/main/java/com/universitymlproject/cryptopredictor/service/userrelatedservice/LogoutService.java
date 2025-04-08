package com.universitymlproject.cryptopredictor.service.userrelatedservice;

import com.universitymlproject.cryptopredictor.config.JwtConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Service
public class LogoutService implements LogoutHandler {

    private JwtService jwtService;
    private JwtConstants jwtConstants;

    public LogoutService(JwtService jwtService, JwtConstants jwtConstants) {
        this.jwtService = jwtService;
        this.jwtConstants = jwtConstants;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String jwt = request.getHeader(jwtConstants.getJWT_HEADER());
        if(jwt != null){
            try{
                SecretKey secretKey = Keys.hmacShaKeyFor(jwtConstants.getSECRET().getBytes(StandardCharsets.UTF_8));
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
