package com.universitymlproject.cryptopredictor.repository;

import com.universitymlproject.cryptopredictor.model.crypto.Bitcoin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BitcoinRepositoryImpl implements BitcoinRepository{

    private EntityManager entityManager;

    public BitcoinRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Bitcoin> getBitcoinHistory(int start, int offset){
        TypedQuery<Bitcoin> query = entityManager.createQuery("FROM Bitcoin", Bitcoin.class);
        start--;
        start = Math.max(0, start);

        query.setFirstResult(start);
        query.setMaxResults(offset);

        return query.getResultList();
    }

    @Override
    @Transactional
    public void persistNewValue(Bitcoin bitcoin){
        entityManager.merge(bitcoin);
    }

}
