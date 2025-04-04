package com.universitymlproject.cryptopredictor.dtomappers.stocks;

import com.universitymlproject.cryptopredictor.dto.stocks.MicrosoftDTOGet;
import com.universitymlproject.cryptopredictor.dto.stocks.MicrosoftDTOPost;
import com.universitymlproject.cryptopredictor.model.stocks.Microsoft;
import org.springframework.stereotype.Service;

@Service
public class MicrosoftMapper implements StocksMapper<Microsoft, MicrosoftDTOGet, MicrosoftDTOPost>{

    public MicrosoftDTOGet toDTO(Microsoft microsoft){
        return new MicrosoftDTOGet(
                microsoft.getId(),
                microsoft.getDate(),
                microsoft.getClose(),
                microsoft.getVolume(),
                microsoft.getOpen(),
                microsoft.getHigh(),
                microsoft.getLow()
        );
    }

    public Microsoft toModel(MicrosoftDTOPost microsoftDTO){
        return new Microsoft(
                microsoftDTO.getDate(),
                microsoftDTO.getClose(),
                microsoftDTO.getVolume(),
                microsoftDTO.getOpen(),
                microsoftDTO.getHigh(),
                microsoftDTO.getLow()
        );
    }
    
}
