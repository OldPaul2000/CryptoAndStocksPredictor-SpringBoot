package com.universitymlproject.cryptopredictor.service.stocksservice;

import com.universitymlproject.cryptopredictor.dto.stocks.MicrosoftDTOGet;
import com.universitymlproject.cryptopredictor.dto.stocks.MicrosoftDTOPost;
import com.universitymlproject.cryptopredictor.dtomappers.stocks.StocksMapper;
import com.universitymlproject.cryptopredictor.model.stocks.Microsoft;
import com.universitymlproject.cryptopredictor.repository.stocks.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MicrosoftService {

    private StocksRepository<Microsoft> microsoftRepository;
    private StocksMapper stocksMapper;

    @Autowired
    public MicrosoftService(StocksRepository<Microsoft> microsoftRepository,
                            @Qualifier("microsoftMapper") StocksMapper cryptoMapper) {
        this.microsoftRepository = microsoftRepository;
        this.stocksMapper = cryptoMapper;
    }

    public List<MicrosoftDTOGet> getMicrosoftHistory(int start, int resultsPerPage){
        return microsoftRepository.getHistory(start, resultsPerPage)
                .stream().map(microsoft -> {
                    return (MicrosoftDTOGet)stocksMapper.toDTO(microsoft);
                }).toList();
    }

    public void addMicrosoftRecord(MicrosoftDTOPost microsoftDTO){
        microsoftRepository.persistEntity((Microsoft)stocksMapper.toModel(microsoftDTO));
    }

    public void updateMicrosoftRecord(long id, MicrosoftDTOPost microsoftDTO){
        Microsoft currentMicrosoft = (Microsoft)microsoftRepository.findById(id);
        currentMicrosoft.setDate(microsoftDTO.getDate());
        currentMicrosoft.setClose(microsoftDTO.getClose());
        currentMicrosoft.setVolume(microsoftDTO.getVolume());
        currentMicrosoft.setOpen(microsoftDTO.getOpen());
        currentMicrosoft.setHigh(microsoftDTO.getHigh());
        currentMicrosoft.setLow(microsoftDTO.getLow());

        microsoftRepository.mergeEntity(currentMicrosoft);
    }

    public void deleteMicrosoftRecord(long id){
        Microsoft microsoft = (Microsoft) microsoftRepository.findById(id);
        microsoftRepository.removeEntity(microsoft);
    }
    
}
