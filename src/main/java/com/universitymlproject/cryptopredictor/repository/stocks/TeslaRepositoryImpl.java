package com.universitymlproject.cryptopredictor.repository.stocks;

import com.universitymlproject.cryptopredictor.model.stocks.Tesla;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TeslaRepositoryImpl implements StocksRepository<Tesla>{

    private EntityManager entityManager;

    public TeslaRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Tesla> getHistory(int start, int offset){
        TypedQuery<Tesla> query = entityManager.createQuery("FROM Tesla", Tesla.class);
        start--;
        start = Math.max(0, start);

        query.setFirstResult(start);
        query.setMaxResults(offset);

        return query.getResultList();
    }

    @Override
    public Tesla findById(long id){
        return entityManager.find(Tesla.class, id);
    }

    @Override
    @Transactional
    public void persistEntity(Tesla tesla){
        entityManager.merge(tesla);
    }

    @Override
    @Transactional
    public void mergeEntity(Tesla tesla){
        entityManager.merge(tesla);
    }

    @Override
    @Transactional
    public void removeEntity(Tesla tesla){
        entityManager.remove(tesla);
    }

}
