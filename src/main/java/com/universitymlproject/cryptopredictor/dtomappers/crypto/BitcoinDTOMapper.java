package com.universitymlproject.cryptopredictor.dtomappers.crypto;

import com.universitymlproject.cryptopredictor.dto.crypto.BitcoinDTOGet;
import com.universitymlproject.cryptopredictor.dto.crypto.BitcoinDTOPost;
import com.universitymlproject.cryptopredictor.model.crypto.Bitcoin;
import org.springframework.stereotype.Service;

@Service
public class BitcoinDTOMapper {

    public BitcoinDTOGet toDTO(Bitcoin bitcoin){
        return new BitcoinDTOGet(
                bitcoin.getId(),
                bitcoin.getLow(),
                bitcoin.getHigh()
        );
    }

    public Bitcoin fromDTO(BitcoinDTOPost bitcoinDTO){
        return new Bitcoin(bitcoinDTO.low(), bitcoinDTO.heigh());
    }

}
