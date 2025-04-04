package com.universitymlproject.cryptopredictor.dtomappers.crypto;

import com.universitymlproject.cryptopredictor.dto.crypto.RippleDTOGet;
import com.universitymlproject.cryptopredictor.dto.crypto.RippleDTOPost;
import com.universitymlproject.cryptopredictor.model.crypto.Ripple;
import org.springframework.stereotype.Service;

@Service
public class RippleMapper implements CryptoMapper<Ripple, RippleDTOGet, RippleDTOPost>{

    public RippleDTOGet toDTO(Ripple ripple){
        return new RippleDTOGet(
                ripple.getId(),
                ripple.getDate(),
                ripple.getClose(),
                ripple.getVolume(),
                ripple.getOpen(),
                ripple.getHigh(),
                ripple.getLow()
        );
    }

    public Ripple toModel(RippleDTOPost rippleDTO){
        return new Ripple(
                rippleDTO.getDate(),
                rippleDTO.getClose(),
                rippleDTO.getVolume(),
                rippleDTO.getOpen(),
                rippleDTO.getHigh(),
                rippleDTO.getLow()
        );
    }
    
}
