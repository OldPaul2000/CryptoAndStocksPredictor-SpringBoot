package com.universitymlproject.cryptopredictor.repository.crypto;

import com.universitymlproject.cryptopredictor.model.crypto.Ethereum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EthereumRepositoryImpl implements CryptoRepository<Ethereum>{

    private EntityManager entityManager;

    public EthereumRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Ethereum> getHistory(int start, int offset){
        TypedQuery<Ethereum> query = entityManager.createQuery("FROM Ethereum", Ethereum.class);
        start--;
        start = Math.max(0, start);

        query.setFirstResult(start);
        query.setMaxResults(offset);

        return query.getResultList();
    }

    @Override
    public Ethereum findById(long id){
        return entityManager.find(Ethereum.class, id);
    }

    @Override
    @Transactional
    public void persistEntity(Ethereum ethereum){
        entityManager.merge(ethereum);
    }

    @Override
    @Transactional
    public void mergeEntity(Ethereum ethereum){
        entityManager.merge(ethereum);
    }

    @Override
    @Transactional
    public void removeEntity(Ethereum ethereum){
        entityManager.remove(ethereum);
    }

}
