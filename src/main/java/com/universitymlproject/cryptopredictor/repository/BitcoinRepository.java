package com.universitymlproject.cryptopredictor.repository;

import com.universitymlproject.cryptopredictor.model.crypto.Bitcoin;

import java.util.List;

public interface BitcoinRepository {

    List<Bitcoin> getBitcoinHistory(int start, int offset);

    void persistNewValue(Bitcoin bitcoin);

}
