package com.universitymlproject.cryptopredictor.repository.stocks;

import com.universitymlproject.cryptopredictor.model.stocks.Stock;

import java.util.List;

public interface StocksRepository<T extends Stock> {

    List<T> getHistory(int start, int offset);

    Stock findById(long id);

    void persistEntity(T stock);

    void mergeEntity(T stock);

    void removeEntity(T stock);

}
