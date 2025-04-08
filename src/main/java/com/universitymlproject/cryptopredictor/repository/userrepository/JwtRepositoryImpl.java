package com.universitymlproject.cryptopredictor.repository.userrepository;

import com.universitymlproject.cryptopredictor.model.userrelated.Jwt;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JwtRepositoryImpl implements JwtRepository{

    private EntityManager entityManager;

    public JwtRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Jwt findJWTByUserId(long userId) {
        TypedQuery<Jwt> query = entityManager.createQuery("SELECT j FROM Jwt j " +
                                                          "RIGHT JOIN FETCH j.user " +
                                                          "WHERE j.user.id = :data", Jwt.class);
        query.setParameter("data", userId);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void persistToken(Jwt jwt){
        entityManager.persist(jwt);
    }

    @Override
    @Transactional
    public void mergeToken(Jwt jwt) {
        entityManager.merge(jwt);
    }

    @Override
    @Transactional
    public void removeToken(Jwt jwt) {
        entityManager.remove(jwt);
    }
}
