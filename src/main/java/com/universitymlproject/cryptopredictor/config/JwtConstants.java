package com.universitymlproject.cryptopredictor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConstants {

    @Value("${jwt.header}")
    private String JWT_HEADER;

    @Value("${jwt.secret-key}")
    private String SECRET;

    public String getJWT_HEADER() {
        return JWT_HEADER;
    }

    public void setJWT_HEADER(String JWT_HEADER) {
        this.JWT_HEADER = JWT_HEADER;
    }

    public String getSECRET() {
        return SECRET;
    }

    public void setSECRET(String SECRET) {
        this.SECRET = SECRET;
    }
}
