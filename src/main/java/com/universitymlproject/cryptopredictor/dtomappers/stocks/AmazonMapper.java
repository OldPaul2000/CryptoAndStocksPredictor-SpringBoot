package com.universitymlproject.cryptopredictor.dtomappers.stocks;

import com.universitymlproject.cryptopredictor.dto.stocks.AmazonDTOGet;
import com.universitymlproject.cryptopredictor.dto.stocks.AmazonDTOPost;
import com.universitymlproject.cryptopredictor.model.stocks.Amazon;
import org.springframework.stereotype.Service;

@Service
public class AmazonMapper implements StocksMapper<Amazon, AmazonDTOGet, AmazonDTOPost>{

    public AmazonDTOGet toDTO(Amazon amazon){
        return new AmazonDTOGet(
                amazon.getId(),
                amazon.getDate(),
                amazon.getClose(),
                amazon.getVolume(),
                amazon.getOpen(),
                amazon.getHigh(),
                amazon.getLow()
        );
    }

    public Amazon toModel(AmazonDTOPost amazonDTO){
        return new Amazon(
                amazonDTO.getDate(),
                amazonDTO.getClose(),
                amazonDTO.getVolume(),
                amazonDTO.getOpen(),
                amazonDTO.getHigh(),
                amazonDTO.getLow()
        );
    }

}
