package com.universitymlproject.cryptopredictor.dtomappers.crypto;

import com.universitymlproject.cryptopredictor.dto.crypto.LitecoinDTOGet;
import com.universitymlproject.cryptopredictor.dto.crypto.LitecoinDTOPost;
import com.universitymlproject.cryptopredictor.model.crypto.Litecoin;
import org.springframework.stereotype.Service;

@Service
public class LitecoinMapper implements CryptoMapper<Litecoin, LitecoinDTOGet, LitecoinDTOPost>{

    public LitecoinDTOGet toDTO(Litecoin litecoin){
        return new LitecoinDTOGet(
                litecoin.getId(),
                litecoin.getDate(),
                litecoin.getClose(),
                litecoin.getVolume(),
                litecoin.getOpen(),
                litecoin.getHigh(),
                litecoin.getLow()
        );
    }

    public Litecoin toModel(LitecoinDTOPost litecoinDTO){
        return new Litecoin(
                litecoinDTO.getDate(),
                litecoinDTO.getClose(),
                litecoinDTO.getVolume(),
                litecoinDTO.getOpen(),
                litecoinDTO.getHigh(),
                litecoinDTO.getLow()
        );
    }
    
}
