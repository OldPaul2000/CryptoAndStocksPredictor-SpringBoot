package com.universitymlproject.cryptopredictor.dtomappers.stocks;

import com.universitymlproject.cryptopredictor.dto.stocks.StocksDTOGet;
import com.universitymlproject.cryptopredictor.dto.stocks.StocksDTOPost;
import com.universitymlproject.cryptopredictor.model.stocks.Stock;

public interface StocksMapper<V extends Stock, G extends StocksDTOGet, P extends StocksDTOPost> {

    G toDTO(V stock);

    V toModel(P stockDTO);

}
