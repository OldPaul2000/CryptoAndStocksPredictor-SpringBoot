package com.universitymlproject.cryptopredictor.service.stocksservice;

import com.universitymlproject.cryptopredictor.dto.stocks.StarbucksDTOGet;
import com.universitymlproject.cryptopredictor.dto.stocks.StarbucksDTOPost;
import com.universitymlproject.cryptopredictor.dtomappers.stocks.StocksMapper;
import com.universitymlproject.cryptopredictor.model.stocks.Starbucks;
import com.universitymlproject.cryptopredictor.repository.stocks.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StarbucksService {

    private StocksRepository<Starbucks> starbucksRepository;
    private StocksMapper stocksMapper;

    @Autowired
    public StarbucksService(StocksRepository<Starbucks> starbucksRepository,
                            @Qualifier("starbucksMapper")  StocksMapper cryptoMapper) {
        this.starbucksRepository = starbucksRepository;
        this.stocksMapper = cryptoMapper;
    }

    public List<StarbucksDTOGet> getStarbucksHistory(int start, int resultsPerPage){
        return starbucksRepository.getHistory(start, resultsPerPage)
                .stream().map(starbucks -> {
                    return (StarbucksDTOGet)stocksMapper.toDTO(starbucks);
                }).toList();
    }

    public void addStarbucksRecord(StarbucksDTOPost starbucksDTO){
        starbucksRepository.persistEntity((Starbucks)stocksMapper.toModel(starbucksDTO));
    }

    public void updateStarbucksRecord(long id, StarbucksDTOPost starbucksDTO){
        Starbucks currentStarbucks = (Starbucks)starbucksRepository.findById(id);
        currentStarbucks.setDate(starbucksDTO.getDate());
        currentStarbucks.setClose(starbucksDTO.getClose());
        currentStarbucks.setVolume(starbucksDTO.getVolume());
        currentStarbucks.setOpen(starbucksDTO.getOpen());
        currentStarbucks.setHigh(starbucksDTO.getHigh());
        currentStarbucks.setLow(starbucksDTO.getLow());

        starbucksRepository.mergeEntity(currentStarbucks);
    }

    public void deleteStarbucksRecord(long id){
        Starbucks starbucks = (Starbucks) starbucksRepository.findById(id);
        starbucksRepository.removeEntity(starbucks);
    }
    
}
