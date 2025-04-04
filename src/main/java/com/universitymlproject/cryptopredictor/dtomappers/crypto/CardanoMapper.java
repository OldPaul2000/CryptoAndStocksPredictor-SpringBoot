package com.universitymlproject.cryptopredictor.dtomappers.crypto;

import com.universitymlproject.cryptopredictor.dto.crypto.CardanoDTOGet;
import com.universitymlproject.cryptopredictor.dto.crypto.CardanoDTOPost;
import com.universitymlproject.cryptopredictor.model.crypto.Cardano;
import org.springframework.stereotype.Service;

@Service
public class CardanoMapper implements CryptoMapper<Cardano, CardanoDTOGet, CardanoDTOPost>{

    public CardanoDTOGet toDTO(Cardano cardano){
        return new CardanoDTOGet(
                cardano.getId(),
                cardano.getDate(),
                cardano.getClose(),
                cardano.getVolume(),
                cardano.getOpen(),
                cardano.getHigh(),
                cardano.getLow()
        );
    }

    public Cardano toModel(CardanoDTOPost cardanoDTO){
        return new Cardano(
                cardanoDTO.getDate(),
                cardanoDTO.getClose(),
                cardanoDTO.getVolume(),
                cardanoDTO.getOpen(),
                cardanoDTO.getHigh(),
                cardanoDTO.getLow()
        );
    }
    
}
