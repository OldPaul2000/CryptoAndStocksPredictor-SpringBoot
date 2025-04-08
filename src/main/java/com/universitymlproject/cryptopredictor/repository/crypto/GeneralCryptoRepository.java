package com.universitymlproject.cryptopredictor.repository.crypto;

import com.universitymlproject.cryptopredictor.model.crypto.Crypto;

import java.util.List;

public interface GeneralCryptoRepository{

    <T extends Crypto> List<T> getHistory(Class<T> crypto, int start, int resultsPerPage);

    <T extends Crypto> T findById(Class<T> crypto, long id);

    <T extends Crypto> T findLastRecord(Class<T> crypto);

    void persistEntity(Crypto crypto);

    void mergeEntity(Crypto crypto);

    void removeEntity(Crypto crypto);

}
