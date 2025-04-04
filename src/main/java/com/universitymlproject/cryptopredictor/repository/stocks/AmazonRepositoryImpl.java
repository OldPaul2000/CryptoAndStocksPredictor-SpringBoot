package com.universitymlproject.cryptopredictor.repository.stocks;

import com.universitymlproject.cryptopredictor.model.stocks.Amazon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AmazonRepositoryImpl implements StocksRepository<Amazon>{

    private EntityManager entityManager;

    public AmazonRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Amazon> getHistory(int start, int offset){
        TypedQuery<Amazon> query = entityManager.createQuery("FROM Amazon", Amazon.class);
        start--;
        start = Math.max(0, start);

        query.setFirstResult(start);
        query.setMaxResults(offset);

        return query.getResultList();
    }

    @Override
    public Amazon findById(long id){
        return entityManager.find(Amazon.class, id);
    }

    @Override
    @Transactional
    public void persistEntity(Amazon amazon){
        entityManager.merge(amazon);
    }

    @Override
    @Transactional
    public void mergeEntity(Amazon amazon){
        entityManager.merge(amazon);
    }

    @Override
    @Transactional
    public void removeEntity(Amazon amazon){
        entityManager.remove(amazon);
    }

}
