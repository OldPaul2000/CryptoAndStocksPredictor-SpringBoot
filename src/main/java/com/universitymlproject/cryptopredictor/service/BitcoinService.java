package com.universitymlproject.cryptopredictor.service;

import com.universitymlproject.cryptopredictor.dto.crypto.BitcoinDTOGet;
import com.universitymlproject.cryptopredictor.dto.crypto.BitcoinDTOPost;
import com.universitymlproject.cryptopredictor.dtomappers.crypto.BitcoinDTOMapper;
import com.universitymlproject.cryptopredictor.repository.BitcoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BitcoinService {

    private BitcoinRepository bitcoinRepository;
    private BitcoinDTOMapper bitcoinDTOMapper;

    @Autowired
    public BitcoinService(BitcoinRepository bitcoinRepository, BitcoinDTOMapper bitcoinDTOMapper) {
        this.bitcoinRepository = bitcoinRepository;
        this.bitcoinDTOMapper = bitcoinDTOMapper;
    }


    public List<BitcoinDTOGet> getBitcoinHistory(int start, int resultsPerPage){
        return bitcoinRepository.getBitcoinHistory(start, resultsPerPage)
                .stream().map(bitcoin -> {
                    return bitcoinDTOMapper.toDTO(bitcoin);
                }).toList();
    }

    public void addNewBitcoinValues(BitcoinDTOPost bitcoinDTO){
        bitcoinRepository.persistNewValue(bitcoinDTOMapper.fromDTO(bitcoinDTO));
    }
}
