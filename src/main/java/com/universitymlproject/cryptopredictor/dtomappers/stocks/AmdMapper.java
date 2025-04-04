package com.universitymlproject.cryptopredictor.dtomappers.stocks;

import com.universitymlproject.cryptopredictor.dto.stocks.AmdDTOGet;
import com.universitymlproject.cryptopredictor.dto.stocks.AmdDTOPost;
import com.universitymlproject.cryptopredictor.model.stocks.Amd;
import org.springframework.stereotype.Service;

@Service
public class AmdMapper implements StocksMapper<Amd, AmdDTOGet, AmdDTOPost>{

    public AmdDTOGet toDTO(Amd amd){
        return new AmdDTOGet(
                amd.getId(),
                amd.getDate(),
                amd.getClose(),
                amd.getVolume(),
                amd.getOpen(),
                amd.getHigh(),
                amd.getLow()
        );
    }

    public Amd toModel(AmdDTOPost amdDTO){
        return new Amd(
                amdDTO.getDate(),
                amdDTO.getClose(),
                amdDTO.getVolume(),
                amdDTO.getOpen(),
                amdDTO.getHigh(),
                amdDTO.getLow()
        );
    }
    
}
