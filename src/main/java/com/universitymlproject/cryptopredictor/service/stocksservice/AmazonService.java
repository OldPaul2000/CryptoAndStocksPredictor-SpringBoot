package com.universitymlproject.cryptopredictor.service.stocksservice;

import com.universitymlproject.cryptopredictor.dto.stocks.AmazonDTOGet;
import com.universitymlproject.cryptopredictor.dto.stocks.AmazonDTOPost;
import com.universitymlproject.cryptopredictor.dtomappers.stocks.StocksMapper;
import com.universitymlproject.cryptopredictor.model.stocks.Amazon;
import com.universitymlproject.cryptopredictor.repository.stocks.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmazonService {

    private StocksRepository<Amazon> amazonRepository;
    private StocksMapper<Amazon, AmazonDTOGet, AmazonDTOPost> stocksMapper;

    @Autowired
    public AmazonService(StocksRepository<Amazon> amazonRepository,
                         @Qualifier("amazonMapper") StocksMapper<Amazon, AmazonDTOGet, AmazonDTOPost> cryptoMapper) {
        this.amazonRepository = amazonRepository;
        this.stocksMapper = cryptoMapper;
    }

    public List<AmazonDTOGet> getAmazonHistory(int start, int resultsPerPage){
        return amazonRepository.getHistory(start, resultsPerPage)
                .stream().map(amazon -> {
                    return (AmazonDTOGet)stocksMapper.toDTO(amazon);
                }).toList();
    }

    public void addAmazonRecord(AmazonDTOPost amazonDTO){
        amazonRepository.persistEntity((Amazon)stocksMapper.toModel(amazonDTO));
    }

    public void updateAmazonRecord(long id, AmazonDTOPost amazonDTO){
        Amazon currentAmazon = (Amazon)amazonRepository.findById(id);
        currentAmazon.setDate(amazonDTO.getDate());
        currentAmazon.setClose(amazonDTO.getClose());
        currentAmazon.setVolume(amazonDTO.getVolume());
        currentAmazon.setOpen(amazonDTO.getOpen());
        currentAmazon.setHigh(amazonDTO.getHigh());
        currentAmazon.setLow(amazonDTO.getLow());

        amazonRepository.mergeEntity(currentAmazon);
    }

    public void deleteAmazonRecord(long id){
        Amazon amazon = (Amazon) amazonRepository.findById(id);
        amazonRepository.removeEntity(amazon);
    }
    
}
