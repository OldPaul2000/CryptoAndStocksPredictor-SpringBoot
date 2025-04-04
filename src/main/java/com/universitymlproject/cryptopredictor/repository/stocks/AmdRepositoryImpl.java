package com.universitymlproject.cryptopredictor.repository.stocks;

import com.universitymlproject.cryptopredictor.model.stocks.Amd;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AmdRepositoryImpl implements StocksRepository<Amd>{

    private EntityManager entityManager;

    public AmdRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Amd> getHistory(int start, int offset){
        TypedQuery<Amd> query = entityManager.createQuery("FROM Amd", Amd.class);
        start--;
        start = Math.max(0, start);

        query.setFirstResult(start);
        query.setMaxResults(offset);

        return query.getResultList();
    }

    @Override
    public Amd findById(long id){
        return entityManager.find(Amd.class, id);
    }

    @Override
    @Transactional
    public void persistEntity(Amd amd){
        entityManager.merge(amd);
    }

    @Override
    @Transactional
    public void mergeEntity(Amd amd){
        entityManager.merge(amd);
    }

    @Override
    @Transactional
    public void removeEntity(Amd amd){
        entityManager.remove(amd);
    }

}
