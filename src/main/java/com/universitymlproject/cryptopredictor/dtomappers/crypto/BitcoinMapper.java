package com.universitymlproject.cryptopredictor.dtomappers.crypto;

import com.universitymlproject.cryptopredictor.dto.crypto.BitcoinDTOGet;
import com.universitymlproject.cryptopredictor.dto.crypto.BitcoinDTOPost;
import com.universitymlproject.cryptopredictor.model.crypto.Bitcoin;
import org.springframework.stereotype.Service;

@Service
public class BitcoinMapper implements CryptoMapper<Bitcoin, BitcoinDTOGet, BitcoinDTOPost>{

    public BitcoinDTOGet toDTO(Bitcoin bitcoin){
        return new BitcoinDTOGet(
                bitcoin.getId(),
                bitcoin.getDate(),
                bitcoin.getClose(),
                bitcoin.getVolume(),
                bitcoin.getOpen(),
                bitcoin.getHigh(),
                bitcoin.getLow()
        );
    }

    public Bitcoin toModel(BitcoinDTOPost bitcoinDTO){
        return new Bitcoin(
                bitcoinDTO.getDate(),
                bitcoinDTO.getClose(),
                bitcoinDTO.getVolume(),
                bitcoinDTO.getOpen(),
                bitcoinDTO.getHigh(),
                bitcoinDTO.getLow()
        );
    }

}
