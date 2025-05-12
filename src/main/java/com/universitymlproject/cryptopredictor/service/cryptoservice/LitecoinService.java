package com.universitymlproject.cryptopredictor.service.cryptoservice;

import com.universitymlproject.cryptopredictor.dto.crypto.LitecoinDTOGet;
import com.universitymlproject.cryptopredictor.dto.crypto.LitecoinDTOPost;
import com.universitymlproject.cryptopredictor.dtomappers.crypto.CryptoMapper;
import com.universitymlproject.cryptopredictor.model.crypto.Litecoin;
import com.universitymlproject.cryptopredictor.repository.crypto.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LitecoinService {

    private CryptoRepository<Litecoin> litecoinRepository;
    private CryptoMapper<Litecoin, LitecoinDTOGet, LitecoinDTOPost> cryptoMapper;

    @Autowired
    public LitecoinService(CryptoRepository<Litecoin> litecoinRepository,
                           @Qualifier("litecoinMapper") CryptoMapper<Litecoin, LitecoinDTOGet, LitecoinDTOPost> cryptoMapper) {
        this.litecoinRepository = litecoinRepository;
        this.cryptoMapper = cryptoMapper;
    }

    public List<LitecoinDTOGet> getLitecoinHistory(int start, int resultsPerPage){
        return litecoinRepository.getHistory(start, resultsPerPage)
                .stream().map(litecoin -> {
                    return (LitecoinDTOGet)cryptoMapper.toDTO(litecoin);
                }).toList();
    }

    public void addLitecoinRecord(LitecoinDTOPost litecoinDTO){
        litecoinRepository.persistEntity((Litecoin) cryptoMapper.toModel(litecoinDTO));
    }

    public void updateLitecoinRecord(long id, LitecoinDTOPost litecoinDTO){
        Litecoin currentLitecoin = (Litecoin) litecoinRepository.findById(id);
        currentLitecoin.setDate(litecoinDTO.getDate());
        currentLitecoin.setClose(litecoinDTO.getClose());
        currentLitecoin.setVolume(litecoinDTO.getVolume());
        currentLitecoin.setOpen(litecoinDTO.getOpen());
        currentLitecoin.setHigh(litecoinDTO.getHigh());
        currentLitecoin.setLow(litecoinDTO.getLow());

        litecoinRepository.mergeEntity(currentLitecoin);
    }

    public void deleteLitecoinRecord(long id){
        Litecoin litecoin = (Litecoin) litecoinRepository.findById(id);
        litecoinRepository.removeEntity(litecoin);
    }

}
