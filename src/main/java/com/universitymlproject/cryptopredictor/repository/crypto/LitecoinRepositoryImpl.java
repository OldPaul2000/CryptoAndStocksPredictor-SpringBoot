package com.universitymlproject.cryptopredictor.repository.crypto;

import com.universitymlproject.cryptopredictor.model.crypto.Litecoin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class LitecoinRepositoryImpl implements CryptoRepository<Litecoin> {

    private EntityManager entityManager;

    public LitecoinRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Litecoin> getHistory(int start, int offset){
        TypedQuery<Litecoin> query = entityManager.createQuery("FROM Litecoin", Litecoin.class);
        start--;
        start = Math.max(0, start);

        query.setFirstResult(start);
        query.setMaxResults(offset);

        return query.getResultList();
    }

    @Override
    public Litecoin findById(long id){
        return entityManager.find(Litecoin.class, id);
    }

    @Override
    @Transactional
    public void persistEntity(Litecoin litecoin){
        entityManager.merge(litecoin);
    }

    @Override
    @Transactional
    public void mergeEntity(Litecoin litecoin){
        entityManager.merge(litecoin);
    }

    @Override
    @Transactional
    public void removeEntity(Litecoin litecoin){
        entityManager.remove(litecoin);
    }

}
