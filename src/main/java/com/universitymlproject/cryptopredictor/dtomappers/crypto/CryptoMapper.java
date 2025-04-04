package com.universitymlproject.cryptopredictor.dtomappers.crypto;

import com.universitymlproject.cryptopredictor.dto.crypto.CryptoDTOGet;
import com.universitymlproject.cryptopredictor.dto.crypto.CryptoDTOPost;
import com.universitymlproject.cryptopredictor.model.crypto.CryptoCurrency;

public interface CryptoMapper<V extends CryptoCurrency, G extends CryptoDTOGet, P extends CryptoDTOPost> {

    G toDTO(V cryptoCurrency);

    V toModel(P cryptoDTO);

}
