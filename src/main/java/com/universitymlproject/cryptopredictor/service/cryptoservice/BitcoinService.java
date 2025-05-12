package com.universitymlproject.cryptopredictor.service.cryptoservice;

import com.universitymlproject.cryptopredictor.dto.crypto.BitcoinDTOGet;
import com.universitymlproject.cryptopredictor.dto.crypto.BitcoinDTOPost;
import com.universitymlproject.cryptopredictor.dtomappers.crypto.CryptoMapper;
import com.universitymlproject.cryptopredictor.model.crypto.Bitcoin;
import com.universitymlproject.cryptopredictor.repository.crypto.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BitcoinService {

    private CryptoRepository<Bitcoin> bitcoinRepository;
    private CryptoMapper<Bitcoin, BitcoinDTOGet, BitcoinDTOPost> cryptoMapper;

    @Autowired
    public BitcoinService(CryptoRepository<Bitcoin> bitcoinRepository,
                          @Qualifier("bitcoinMapper") CryptoMapper<Bitcoin, BitcoinDTOGet, BitcoinDTOPost> cryptoMapper) {
        this.bitcoinRepository = bitcoinRepository;
        this.cryptoMapper = cryptoMapper;
    }

    public List<BitcoinDTOGet> getBitcoinHistory(int start, int resultsPerPage){
        return bitcoinRepository.getHistory(start, resultsPerPage)
                .stream().map(bitcoin -> {
                    return cryptoMapper.toDTO(bitcoin);
                }).toList();
    }

    public void addBitcoinRecord(BitcoinDTOPost bitcoinDTO){
        bitcoinRepository.persistEntity(cryptoMapper.toModel(bitcoinDTO));
    }

    public void updateBitcoinRecord(long id, BitcoinDTOPost bitcoinDTO){
        Bitcoin currentBitcoin = (Bitcoin)bitcoinRepository.findById(id);
        currentBitcoin.setDate(bitcoinDTO.getDate());
        currentBitcoin.setClose(bitcoinDTO.getClose());
        currentBitcoin.setVolume(bitcoinDTO.getVolume());
        currentBitcoin.setOpen(bitcoinDTO.getOpen());
        currentBitcoin.setHigh(bitcoinDTO.getHigh());
        currentBitcoin.setLow(bitcoinDTO.getLow());

        bitcoinRepository.mergeEntity(currentBitcoin);
    }

    @Transactional
    public void deleteBitcoinRecord(long id){
        Bitcoin bitcoin = (Bitcoin) bitcoinRepository.findById(id);
        bitcoinRepository.removeEntity(bitcoin);
    }
}
