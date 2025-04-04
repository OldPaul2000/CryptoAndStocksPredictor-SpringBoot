package com.universitymlproject.cryptopredictor.repository.crypto;

import com.universitymlproject.cryptopredictor.model.crypto.Cardano;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CardanoRepositoryImpl implements CryptoRepository<Cardano>{

    private EntityManager entityManager;

    public CardanoRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Cardano> getHistory(int start, int offset){
        TypedQuery<Cardano> query = entityManager.createQuery("FROM Cardano", Cardano.class);
        start--;
        start = Math.max(0, start);

        query.setFirstResult(start);
        query.setMaxResults(offset);

        return query.getResultList();
    }

    @Override
    public Cardano findById(long id){
        return entityManager.find(Cardano.class, id);
    }

    @Override
    @Transactional
    public void persistEntity(Cardano cardano){
        entityManager.merge(cardano);
    }

    @Override
    @Transactional
    public void mergeEntity(Cardano cardano){
        entityManager.merge(cardano);
    }

    @Override
    @Transactional
    public void removeEntity(Cardano cardano){
        entityManager.remove(cardano);
    }

}
