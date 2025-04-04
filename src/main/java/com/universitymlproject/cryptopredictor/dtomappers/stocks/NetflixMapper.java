package com.universitymlproject.cryptopredictor.dtomappers.stocks;

import com.universitymlproject.cryptopredictor.dto.stocks.NetflixDTOGet;
import com.universitymlproject.cryptopredictor.dto.stocks.NetflixDTOPost;
import com.universitymlproject.cryptopredictor.model.stocks.Netflix;
import org.springframework.stereotype.Service;

@Service
public class NetflixMapper implements StocksMapper<Netflix, NetflixDTOGet, NetflixDTOPost>{

    public NetflixDTOGet toDTO(Netflix netflix){
        return new NetflixDTOGet(
                netflix.getId(),
                netflix.getDate(),
                netflix.getClose(),
                netflix.getVolume(),
                netflix.getOpen(),
                netflix.getHigh(),
                netflix.getLow()
        );
    }

    public Netflix toModel(NetflixDTOPost netflixDTO){
        return new Netflix(
                netflixDTO.getDate(),
                netflixDTO.getClose(),
                netflixDTO.getVolume(),
                netflixDTO.getOpen(),
                netflixDTO.getHigh(),
                netflixDTO.getLow()
        );
    }
    
}
