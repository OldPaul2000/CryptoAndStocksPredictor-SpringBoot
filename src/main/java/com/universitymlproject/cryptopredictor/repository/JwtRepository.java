package com.universitymlproject.cryptopredictor.repository;

import com.universitymlproject.cryptopredictor.model.userrelated.Jwt;

public interface JwtRepository {

    Jwt findJWTByUserId(long userId);

    void persistToken(Jwt jwt);

    void mergeToken(Jwt jwt);

    void removeToken(Jwt jwt);

}
