package com.universitymlproject.cryptopredictor.dtomappers.crypto;

import com.universitymlproject.cryptopredictor.dto.crypto.EthereumDTOGet;
import com.universitymlproject.cryptopredictor.dto.crypto.EthereumDTOPost;
import com.universitymlproject.cryptopredictor.model.crypto.Ethereum;
import org.springframework.stereotype.Service;

@Service
public class EthereumMapper implements CryptoMapper<Ethereum, EthereumDTOGet, EthereumDTOPost>{

    public EthereumDTOGet toDTO(Ethereum ethereum){
        return new EthereumDTOGet(
                ethereum.getId(),
                ethereum.getDate(),
                ethereum.getClose(),
                ethereum.getVolume(),
                ethereum.getOpen(),
                ethereum.getHigh(),
                ethereum.getLow()
        );
    }

    public Ethereum toModel(EthereumDTOPost ethereumDTO){
        return new Ethereum(
                ethereumDTO.getDate(),
                ethereumDTO.getClose(),
                ethereumDTO.getVolume(),
                ethereumDTO.getOpen(),
                ethereumDTO.getHigh(),
                ethereumDTO.getLow()
        );
    }
    
}
