package com.universitymlproject.cryptopredictor.service.stocksservice;

import com.universitymlproject.cryptopredictor.dto.stocks.AppleDTOGet;
import com.universitymlproject.cryptopredictor.dto.stocks.AppleDTOPost;
import com.universitymlproject.cryptopredictor.dtomappers.stocks.StocksMapper;
import com.universitymlproject.cryptopredictor.model.stocks.Apple;
import com.universitymlproject.cryptopredictor.repository.stocks.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppleService {

    private StocksRepository<Apple> appleRepository;
    private StocksMapper stocksMapper;

    @Autowired
    public AppleService(StocksRepository<Apple> appleRepository,
                        @Qualifier("appleMapper") StocksMapper cryptoMapper) {
        this.appleRepository = appleRepository;
        this.stocksMapper = cryptoMapper;
    }

    public List<AppleDTOGet> getAppleHistory(int start, int resultsPerPage){
        return appleRepository.getHistory(start, resultsPerPage)
                .stream().map(apple -> {
                    return (AppleDTOGet)stocksMapper.toDTO(apple);
                }).toList();
    }

    public void addAppleRecord(AppleDTOPost appleDTO){
        appleRepository.persistEntity((Apple)stocksMapper.toModel(appleDTO));
    }

    public void updateAppleRecord(long id, AppleDTOPost appleDTO){
        Apple currentApple = (Apple)appleRepository.findById(id);
        currentApple.setDate(appleDTO.getDate());
        currentApple.setClose(appleDTO.getClose());
        currentApple.setVolume(appleDTO.getVolume());
        currentApple.setOpen(appleDTO.getOpen());
        currentApple.setHigh(appleDTO.getHigh());
        currentApple.setLow(appleDTO.getLow());

        appleRepository.mergeEntity(currentApple);
    }

    public void deleteAppleRecord(long id){
        Apple apple = (Apple) appleRepository.findById(id);
        appleRepository.removeEntity(apple);
    }
    
}
