package com.universitymlproject.cryptopredictor.repository.stocks;

import com.universitymlproject.cryptopredictor.model.stocks.Microsoft;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MicrosoftRepositoryImpl implements StocksRepository<Microsoft>{

    private EntityManager entityManager;

    public MicrosoftRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Microsoft> getHistory(int start, int offset){
        TypedQuery<Microsoft> query = entityManager.createQuery("FROM Microsoft", Microsoft.class);
        start--;
        start = Math.max(0, start);

        query.setFirstResult(start);
        query.setMaxResults(offset);

        return query.getResultList();
    }

    @Override
    public Microsoft findById(long id){
        return entityManager.find(Microsoft.class, id);
    }

    @Override
    @Transactional
    public void persistEntity(Microsoft microsoft){
        entityManager.merge(microsoft);
    }

    @Override
    @Transactional
    public void mergeEntity(Microsoft microsoft){
        entityManager.merge(microsoft);
    }

    @Override
    @Transactional
    public void removeEntity(Microsoft microsoft){
        entityManager.remove(microsoft);
    }

}
