package com.universitymlproject.cryptopredictor.service;

import com.universitymlproject.cryptopredictor.model.userrelated.Jwt;
import com.universitymlproject.cryptopredictor.model.userrelated.User;
import com.universitymlproject.cryptopredictor.repository.JwtRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JwtService {

    private JwtRepository jwtRepository;

    public JwtService(JwtRepository jwtRepository) {
        this.jwtRepository = jwtRepository;
    }

    public Jwt getJwtByUserId(long userId){
        return jwtRepository.findJWTByUserId(userId);
    }

    public void addNewToken(User user, String token){
        Jwt currentToken = jwtRepository.findJWTByUserId(user.getId());
        if(currentToken != null){
            currentToken.setJwt(token);
            jwtRepository.mergeToken(currentToken);
        }
        else{
            Jwt newToken = new Jwt(token);
            user.setJwt(newToken);
            user.getJwt().setUser(user);
            jwtRepository.persistToken(newToken);
        }
    };

    @Transactional
    public void invalidateToken(long userId, String currentToken){
        Jwt token = jwtRepository.findJWTByUserId(userId);
        if(token != null && token.getJwt().equals(currentToken)){
            token.getUser().setJwt(null);
            token.setUser(null);
            jwtRepository.removeToken(token);
        }
    }

}
