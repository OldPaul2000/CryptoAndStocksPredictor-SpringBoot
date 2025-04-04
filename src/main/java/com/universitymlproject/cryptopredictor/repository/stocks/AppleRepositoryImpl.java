package com.universitymlproject.cryptopredictor.repository.stocks;

import com.universitymlproject.cryptopredictor.model.stocks.Apple;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppleRepositoryImpl implements StocksRepository<Apple>{

    private EntityManager entityManager;

    public AppleRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Apple> getHistory(int start, int offset){
        TypedQuery<Apple> query = entityManager.createQuery("FROM Apple", Apple.class);
        start--;
        start = Math.max(0, start);

        query.setFirstResult(start);
        query.setMaxResults(offset);

        return query.getResultList();
    }

    @Override
    public Apple findById(long id){
        return entityManager.find(Apple.class, id);
    }

    @Override
    @Transactional
    public void persistEntity(Apple apple){
        entityManager.merge(apple);
    }

    @Override
    @Transactional
    public void mergeEntity(Apple apple){
        entityManager.merge(apple);
    }

    @Override
    @Transactional
    public void removeEntity(Apple apple){
        entityManager.remove(apple);
    }

}
