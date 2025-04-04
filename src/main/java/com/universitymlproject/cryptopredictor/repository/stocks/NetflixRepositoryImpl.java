package com.universitymlproject.cryptopredictor.repository.stocks;

import com.universitymlproject.cryptopredictor.model.stocks.Netflix;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class NetflixRepositoryImpl implements StocksRepository<Netflix> {

    private EntityManager entityManager;

    public NetflixRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Netflix> getHistory(int start, int offset){
        TypedQuery<Netflix> query = entityManager.createQuery("FROM Netflix", Netflix.class);
        start--;
        start = Math.max(0, start);

        query.setFirstResult(start);
        query.setMaxResults(offset);

        return query.getResultList();
    }

    @Override
    public Netflix findById(long id){
        return entityManager.find(Netflix.class, id);
    }

    @Override
    @Transactional
    public void persistEntity(Netflix netflix){
        entityManager.merge(netflix);
    }

    @Override
    @Transactional
    public void mergeEntity(Netflix netflix){
        entityManager.merge(netflix);
    }

    @Override
    @Transactional
    public void removeEntity(Netflix netflix){
        entityManager.remove(netflix);
    }

}
