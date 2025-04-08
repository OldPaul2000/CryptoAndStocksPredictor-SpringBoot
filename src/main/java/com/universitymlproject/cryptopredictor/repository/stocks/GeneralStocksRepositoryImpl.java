package com.universitymlproject.cryptopredictor.repository.stocks;

import com.universitymlproject.cryptopredictor.model.stocks.Stock;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class GeneralStocksRepositoryImpl implements GeneralStocksRepository{

    private EntityManager entityManager;

    public GeneralStocksRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public <T extends Stock> List<T> getHistory(Class<T> stock, int start, int resultsPerPage){
       TypedQuery<T> query = entityManager.createQuery("FROM " + stock.getName(), stock);
       start = Math.max(0, start);
       query.setFirstResult(start);
       query.setMaxResults(resultsPerPage);

       return query.getResultList();
   }

   @Override
   public <T extends Stock> T findById(Class<T> stock, int id){
        return entityManager.find(stock, id);
   }

   @Override
   public <T extends Stock> T findLastRecord(Class<T> stock){
        TypedQuery<T> query = entityManager.createQuery("SELECT s FROM " + stock.getName() + " s " +
                                                        "ORDER BY id DESC LIMIT 1", stock);
        try{
            return query.getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
   }

   @Override
   @Transactional
   public void persistEntity(Stock stock){
        entityManager.persist(stock);
   }

   @Override
   @Transactional
   public void mergeEntity(Stock stock){
        entityManager.merge(stock);
   }

   @Override
   @Transactional
   public void removeEntity(Stock stock){
        entityManager.remove(stock);
   }

}
