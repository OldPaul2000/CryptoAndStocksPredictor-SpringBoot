package com.universitymlproject.cryptopredictor.repository.crypto;

import com.universitymlproject.cryptopredictor.model.crypto.Crypto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class GeneralCryptoRepositoryImpl implements GeneralCryptoRepository{

    private EntityManager entityManager;

    public GeneralCryptoRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public <T extends Crypto> List<T> getHistory(Class<T> crypto, int start, int resultsPerPage){
        TypedQuery<T> query = entityManager.createQuery("FROM " + crypto.getName(), crypto);
        start = Math.max(0, start);
        query.setFirstResult(start);
        query.setMaxResults(resultsPerPage);

        return query.getResultList();
    }

    @Override
    public <T extends Crypto> T findById(Class<T> crypto, long id){
        return entityManager.find(crypto, id);
    }

    @Override
    public <T extends Crypto> T findLastRecord(Class<T> crypto){
        TypedQuery<T> query = entityManager.createQuery("SELECT c FROM " + crypto.getName() + " c " +
                                                        "ORDER BY id DESC LIMIT 1", crypto);
        try{
             return query.getSingleResult();
        }
        catch (NoResultException e){
             return null;
        }
    }

    @Override
    @Transactional
    public void persistEntity(Crypto crypto){
        entityManager.merge(crypto);
    }

    @Override
    @Transactional
    public void mergeEntity(Crypto bitcoin){
        entityManager.merge(bitcoin);
    }

    @Override
    @Transactional
    public void removeEntity(Crypto bitcoin){
        entityManager.remove(bitcoin);
    }

}
