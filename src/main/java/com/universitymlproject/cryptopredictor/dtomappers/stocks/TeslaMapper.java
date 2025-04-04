package com.universitymlproject.cryptopredictor.dtomappers.stocks;

import com.universitymlproject.cryptopredictor.dto.stocks.TeslaDTOGet;
import com.universitymlproject.cryptopredictor.dto.stocks.TeslaDTOPost;
import com.universitymlproject.cryptopredictor.model.stocks.Tesla;
import org.springframework.stereotype.Service;

@Service
public class TeslaMapper implements StocksMapper<Tesla, TeslaDTOGet, TeslaDTOPost>{

    public TeslaDTOGet toDTO(Tesla tesla){
        return new TeslaDTOGet(
                tesla.getId(),
                tesla.getDate(),
                tesla.getClose(),
                tesla.getVolume(),
                tesla.getOpen(),
                tesla.getHigh(),
                tesla.getLow()
        );
    }

    public Tesla toModel(TeslaDTOPost teslaDTO){
        return new Tesla(
                teslaDTO.getDate(),
                teslaDTO.getClose(),
                teslaDTO.getVolume(),
                teslaDTO.getOpen(),
                teslaDTO.getHigh(),
                teslaDTO.getLow()
        );
    }
    
}
