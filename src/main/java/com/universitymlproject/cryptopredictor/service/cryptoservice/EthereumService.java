package com.universitymlproject.cryptopredictor.service.cryptoservice;

import com.universitymlproject.cryptopredictor.dto.crypto.EthereumDTOGet;
import com.universitymlproject.cryptopredictor.dto.crypto.EthereumDTOPost;
import com.universitymlproject.cryptopredictor.dtomappers.crypto.CryptoMapper;
import com.universitymlproject.cryptopredictor.model.crypto.Ethereum;
import com.universitymlproject.cryptopredictor.repository.crypto.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EthereumService {

    private CryptoRepository<Ethereum> ethereumRepository;
    private CryptoMapper<Ethereum, EthereumDTOGet, EthereumDTOPost> cryptoMapper;

    @Autowired
    public EthereumService(CryptoRepository<Ethereum> ethereumRepository,
                           @Qualifier("ethereumMapper") CryptoMapper<Ethereum, EthereumDTOGet, EthereumDTOPost> cryptoMapper) {
        this.ethereumRepository = ethereumRepository;
        this.cryptoMapper = cryptoMapper;
    }

    public List<EthereumDTOGet> getEthereumHistory(int start, int resultsPerPage){
        return ethereumRepository.getHistory(start, resultsPerPage)
                .stream().map(ethereum -> {
                    return (EthereumDTOGet)cryptoMapper.toDTO(ethereum);
                }).toList();
    }

    public void addEthereumRecord(EthereumDTOPost ethereumDTO){
        ethereumRepository.persistEntity((Ethereum)cryptoMapper.toModel(ethereumDTO));
    }

    public void updateEthereumRecord(long id, EthereumDTOPost ethereumDTO){
        Ethereum currentEthereum = (Ethereum) ethereumRepository.findById(id);
        currentEthereum.setDate(ethereumDTO.getDate());
        currentEthereum.setClose(ethereumDTO.getClose());
        currentEthereum.setVolume(ethereumDTO.getVolume());
        currentEthereum.setOpen(ethereumDTO.getOpen());
        currentEthereum.setHigh(ethereumDTO.getHigh());
        currentEthereum.setLow(ethereumDTO.getLow());

        ethereumRepository.mergeEntity(currentEthereum);
    }

    public void deleteEthereumRecord(long id){
        Ethereum ethereum = (Ethereum) ethereumRepository.findById(id);
        ethereumRepository.removeEntity(ethereum);
    }
}
