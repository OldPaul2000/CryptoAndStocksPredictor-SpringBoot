package com.universitymlproject.cryptopredictor.filter;

import com.universitymlproject.cryptopredictor.config.JwtConstants;
import com.universitymlproject.cryptopredictor.model.userrelated.Jwt;
import com.universitymlproject.cryptopredictor.repository.userrepository.JwtRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JwtValidatorFilter extends OncePerRequestFilter {

    private JwtRepository jwtRepository;
    private JwtConstants jwtConstants;

    public JwtValidatorFilter(JwtRepository jwtRepository,
                              JwtConstants jwtConstants) {
        this.jwtRepository = jwtRepository;
        this.jwtConstants = jwtConstants;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader(jwtConstants.getJWT_HEADER());
        if(jwt != null){
            try {
                SecretKey secretKey = Keys.hmacShaKeyFor(jwtConstants.getSECRET().getBytes(StandardCharsets.UTF_8));
                if(secretKey != null){
                    Claims claims = Jwts.parser().verifyWith(secretKey)
                            .build().parseSignedClaims(jwt).getPayload();
                    String username = String.valueOf(claims.get("username"));
                    String authorities = String.valueOf(claims.get("authorities"));
                    long userId = Long.parseLong(String.valueOf(claims.get("userId")));

                    currentTokenIsValid(jwt, userId);
                    Authentication authentication = new UsernamePasswordAuthenticationToken(username, null,
                            AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            catch (InvalidKeyException e){
                response.setHeader("cause", e.getMessage());
            }
            catch (Exception e){
                response.setHeader("cause", "JWT is expired");
            }
        }
        filterChain.doFilter(request, response);
    }

    private void currentTokenIsValid(String currentToken, long userId){
        Jwt jwt = jwtRepository.findJWTByUserId(userId);
        if(jwt == null || !currentToken.equals(jwt.getJwt())){
            throw new InvalidKeyException("JWT is invalid");
        }
    }

}
