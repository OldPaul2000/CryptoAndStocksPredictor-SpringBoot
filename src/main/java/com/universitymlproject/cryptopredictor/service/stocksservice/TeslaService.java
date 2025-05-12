package com.universitymlproject.cryptopredictor.service.stocksservice;

import com.universitymlproject.cryptopredictor.dto.stocks.TeslaDTOGet;
import com.universitymlproject.cryptopredictor.dto.stocks.TeslaDTOPost;
import com.universitymlproject.cryptopredictor.dtomappers.stocks.StocksMapper;
import com.universitymlproject.cryptopredictor.model.stocks.Tesla;
import com.universitymlproject.cryptopredictor.repository.stocks.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeslaService {

    private StocksRepository<Tesla> teslaRepository;
    private StocksMapper<Tesla, TeslaDTOGet, TeslaDTOPost> stocksMapper;

    @Autowired
    public TeslaService(StocksRepository<Tesla> teslaRepository,
                        @Qualifier("teslaMapper") StocksMapper<Tesla, TeslaDTOGet, TeslaDTOPost> cryptoMapper) {
        this.teslaRepository = teslaRepository;
        this.stocksMapper = cryptoMapper;
    }

    public List<TeslaDTOGet> getTeslaHistory(int start, int resultsPerPage){
        return teslaRepository.getHistory(start, resultsPerPage)
                .stream().map(tesla -> {
                    return (TeslaDTOGet)stocksMapper.toDTO(tesla);
                }).toList();
    }

    public void addTeslaRecord(TeslaDTOPost teslaDTO){
        teslaRepository.persistEntity((Tesla)stocksMapper.toModel(teslaDTO));
    }

    public void updateTeslaRecord(long id, TeslaDTOPost teslaDTO){
        Tesla currentTesla = (Tesla)teslaRepository.findById(id);
        currentTesla.setDate(teslaDTO.getDate());
        currentTesla.setClose(teslaDTO.getClose());
        currentTesla.setVolume(teslaDTO.getVolume());
        currentTesla.setOpen(teslaDTO.getOpen());
        currentTesla.setHigh(teslaDTO.getHigh());
        currentTesla.setLow(teslaDTO.getLow());

        teslaRepository.mergeEntity(currentTesla);
    }

    public void deleteTeslaRecord(long id){
        Tesla tesla = (Tesla) teslaRepository.findById(id);
        teslaRepository.removeEntity(tesla);
    }
    
}
