package com.universitymlproject.cryptopredictor.dtomappers.stocks;

import com.universitymlproject.cryptopredictor.dto.stocks.AppleDTOGet;
import com.universitymlproject.cryptopredictor.dto.stocks.AppleDTOPost;
import com.universitymlproject.cryptopredictor.model.stocks.Apple;
import org.springframework.stereotype.Service;

@Service
public class AppleMapper implements StocksMapper<Apple, AppleDTOGet, AppleDTOPost>{

    public AppleDTOGet toDTO(Apple apple){
        return new AppleDTOGet(
                apple.getId(),
                apple.getDate(),
                apple.getClose(),
                apple.getVolume(),
                apple.getOpen(),
                apple.getHigh(),
                apple.getLow()
        );
    }

    public Apple toModel(AppleDTOPost appleDTO){
        return new Apple(
                appleDTO.getDate(),
                appleDTO.getClose(),
                appleDTO.getVolume(),
                appleDTO.getOpen(),
                appleDTO.getHigh(),
                appleDTO.getLow()
        );
    }
    
}
