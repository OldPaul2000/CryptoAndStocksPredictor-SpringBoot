package com.universitymlproject.cryptopredictor.repository.crypto;

import com.universitymlproject.cryptopredictor.model.crypto.Bitcoin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BitcoinRepositoryImpl implements CryptoRepository<Bitcoin>{

    private EntityManager entityManager;

    public BitcoinRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Bitcoin> getHistory(int start, int offset){
        TypedQuery<Bitcoin> query = entityManager.createQuery("FROM Bitcoin", Bitcoin.class);
        start--;
        start = Math.max(0, start);

        query.setFirstResult(start);
        query.setMaxResults(offset);

        return query.getResultList();
    }

    @Override
    public Bitcoin findById(long id){
        return entityManager.find(Bitcoin.class, id);
    }

    @Override
    @Transactional
    public void persistEntity(Bitcoin bitcoin){
        entityManager.merge(bitcoin);
    }

    @Override
    @Transactional
    public void mergeEntity(Bitcoin bitcoin){
        entityManager.merge(bitcoin);
    }

    @Override
    @Transactional
    public void removeEntity(Bitcoin bitcoin){
        entityManager.remove(bitcoin);
    }

}
