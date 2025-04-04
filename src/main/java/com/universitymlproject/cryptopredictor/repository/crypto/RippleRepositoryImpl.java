package com.universitymlproject.cryptopredictor.repository.crypto;

import com.universitymlproject.cryptopredictor.model.crypto.Ripple;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RippleRepositoryImpl implements CryptoRepository<Ripple>{

    private EntityManager entityManager;

    public RippleRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Ripple> getHistory(int start, int offset){
        TypedQuery<Ripple> query = entityManager.createQuery("FROM Ripple", Ripple.class);
        start--;
        start = Math.max(0, start);

        query.setFirstResult(start);
        query.setMaxResults(offset);

        return query.getResultList();
    }

    @Override
    public Ripple findById(long id){
        return entityManager.find(Ripple.class, id);
    }

    @Override
    @Transactional
    public void persistEntity(Ripple ripple){
        entityManager.merge(ripple);
    }

    @Override
    @Transactional
    public void mergeEntity(Ripple ripple){
        entityManager.merge(ripple);
    }

    @Override
    @Transactional
    public void removeEntity(Ripple ripple){
        entityManager.remove(ripple);
    }

}
