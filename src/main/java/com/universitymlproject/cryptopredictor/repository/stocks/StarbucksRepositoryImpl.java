package com.universitymlproject.cryptopredictor.repository.stocks;

import com.universitymlproject.cryptopredictor.model.stocks.Starbucks;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StarbucksRepositoryImpl implements StocksRepository<Starbucks>{

    private EntityManager entityManager;

    public StarbucksRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Starbucks> getHistory(int start, int offset){
        TypedQuery<Starbucks> query = entityManager.createQuery("FROM Starbucks", Starbucks.class);
        start--;
        start = Math.max(0, start);

        query.setFirstResult(start);
        query.setMaxResults(offset);

        return query.getResultList();
    }

    @Override
    public Starbucks findById(long id){
        return entityManager.find(Starbucks.class, id);
    }

    @Override
    @Transactional
    public void persistEntity(Starbucks starbucks){
        entityManager.merge(starbucks);
    }

    @Override
    @Transactional
    public void mergeEntity(Starbucks starbucks){
        entityManager.merge(starbucks);
    }

    @Override
    @Transactional
    public void removeEntity(Starbucks starbucks){
        entityManager.remove(starbucks);
    }

}
