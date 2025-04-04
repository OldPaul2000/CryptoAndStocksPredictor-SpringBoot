package com.universitymlproject.cryptopredictor.repository.crypto;

import com.universitymlproject.cryptopredictor.model.crypto.CryptoCurrency;

import java.util.List;

public interface CryptoRepository<T extends CryptoCurrency>{

    List<T> getHistory(int start, int offset);

    CryptoCurrency findById(long id);

    void persistEntity(T crypto);

    void mergeEntity(T crypto);

    void removeEntity(T crypto);

}
