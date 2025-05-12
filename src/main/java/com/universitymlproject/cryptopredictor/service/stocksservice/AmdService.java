package com.universitymlproject.cryptopredictor.service.stocksservice;

import com.universitymlproject.cryptopredictor.dto.stocks.AmdDTOGet;
import com.universitymlproject.cryptopredictor.dto.stocks.AmdDTOPost;
import com.universitymlproject.cryptopredictor.dtomappers.stocks.StocksMapper;
import com.universitymlproject.cryptopredictor.model.stocks.Amd;
import com.universitymlproject.cryptopredictor.repository.stocks.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmdService {

    private StocksRepository<Amd> amdRepository;
    private StocksMapper<Amd, AmdDTOGet, AmdDTOPost> stocksMapper;

    @Autowired
    public AmdService(StocksRepository<Amd> amdRepository,
                      @Qualifier("amdMapper") StocksMapper<Amd, AmdDTOGet, AmdDTOPost> cryptoMapper) {
        this.amdRepository = amdRepository;
        this.stocksMapper = cryptoMapper;
    }

    public List<AmdDTOGet> getAmdHistory(int start, int resultsPerPage){
        return amdRepository.getHistory(start, resultsPerPage)
                .stream().map(amd -> {
                    return (AmdDTOGet)stocksMapper.toDTO(amd);
                }).toList();
    }

    public void addAmdRecord(AmdDTOPost amdDTO){
        amdRepository.persistEntity((Amd)stocksMapper.toModel(amdDTO));
    }

    public void updateAmdRecord(long id, AmdDTOPost amdDTO){
        Amd currentAmd = (Amd)amdRepository.findById(id);
        currentAmd.setDate(amdDTO.getDate());
        currentAmd.setClose(amdDTO.getClose());
        currentAmd.setVolume(amdDTO.getVolume());
        currentAmd.setOpen(amdDTO.getOpen());
        currentAmd.setHigh(amdDTO.getHigh());
        currentAmd.setLow(amdDTO.getLow());

        amdRepository.mergeEntity(currentAmd);
    }

    public void deleteAmdRecord(long id){
        Amd amd = (Amd) amdRepository.findById(id);
        amdRepository.removeEntity(amd);
    }
    
}
