package com.universitymlproject.cryptopredictor.repository.stocks;

import com.universitymlproject.cryptopredictor.model.stocks.Stock;

import java.util.List;

;

public interface GeneralStocksRepository {

    <T extends Stock> List<T> getHistory(Class<T> stock, int start, int resultsPerPage);

    <T extends Stock> T findById(Class<T> stock, int id);

    <T extends Stock> T findLastRecord(Class<T> stock);

    void persistEntity(Stock stock);

    void mergeEntity(Stock stock);

    void removeEntity(Stock stock);

}
