package com.universitymlproject.cryptopredictor.repository.crypto;

import com.universitymlproject.cryptopredictor.model.crypto.Crypto;

import java.util.List;

public interface CryptoRepository<T extends Crypto>{

    List<T> getHistory(int start, int offset);

    Crypto findById(long id);

    void persistEntity(T crypto);

    void mergeEntity(T crypto);

    void removeEntity(T crypto);

}
