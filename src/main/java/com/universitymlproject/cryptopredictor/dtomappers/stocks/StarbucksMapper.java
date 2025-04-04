package com.universitymlproject.cryptopredictor.dtomappers.stocks;

import com.universitymlproject.cryptopredictor.dto.stocks.StarbucksDTOGet;
import com.universitymlproject.cryptopredictor.dto.stocks.StarbucksDTOPost;
import com.universitymlproject.cryptopredictor.model.stocks.Starbucks;
import org.springframework.stereotype.Service;

@Service
public class StarbucksMapper implements StocksMapper<Starbucks, StarbucksDTOGet, StarbucksDTOPost>{

    public StarbucksDTOGet toDTO(Starbucks starbucks){
        return new StarbucksDTOGet(
                starbucks.getId(),
                starbucks.getDate(),
                starbucks.getClose(),
                starbucks.getVolume(),
                starbucks.getOpen(),
                starbucks.getHigh(),
                starbucks.getLow()
        );
    }

    public Starbucks toModel(StarbucksDTOPost starbucksDTO){
        return new Starbucks(
                starbucksDTO.getDate(),
                starbucksDTO.getClose(),
                starbucksDTO.getVolume(),
                starbucksDTO.getOpen(),
                starbucksDTO.getHigh(),
                starbucksDTO.getLow()
        );
    }
    
}
