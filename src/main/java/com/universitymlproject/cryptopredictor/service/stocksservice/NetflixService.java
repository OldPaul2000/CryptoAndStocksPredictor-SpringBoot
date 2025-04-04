package com.universitymlproject.cryptopredictor.service.stocksservice;

import com.universitymlproject.cryptopredictor.dto.stocks.NetflixDTOGet;
import com.universitymlproject.cryptopredictor.dto.stocks.NetflixDTOPost;
import com.universitymlproject.cryptopredictor.dtomappers.stocks.StocksMapper;
import com.universitymlproject.cryptopredictor.model.stocks.Netflix;
import com.universitymlproject.cryptopredictor.repository.stocks.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NetflixService {

    private StocksRepository<Netflix> netflixRepository;
    private StocksMapper stocksMapper;

    @Autowired
    public NetflixService(StocksRepository<Netflix> netflixRepository,
                          @Qualifier("netflixMapper") StocksMapper cryptoMapper) {
        this.netflixRepository = netflixRepository;
        this.stocksMapper = cryptoMapper;
    }

    public List<NetflixDTOGet> getNetflixHistory(int start, int resultsPerPage){
        return netflixRepository.getHistory(start, resultsPerPage)
                .stream().map(netflix -> {
                    return (NetflixDTOGet)stocksMapper.toDTO(netflix);
                }).toList();
    }

    public void addNetflixRecord(NetflixDTOPost netflixDTO){
        netflixRepository.persistEntity((Netflix)stocksMapper.toModel(netflixDTO));
    }

    public void updateNetflixRecord(long id, NetflixDTOPost netflixDTO){
        Netflix currentNetflix = (Netflix)netflixRepository.findById(id);
        currentNetflix.setDate(netflixDTO.getDate());
        currentNetflix.setClose(netflixDTO.getClose());
        currentNetflix.setVolume(netflixDTO.getVolume());
        currentNetflix.setOpen(netflixDTO.getOpen());
        currentNetflix.setHigh(netflixDTO.getHigh());
        currentNetflix.setLow(netflixDTO.getLow());

        netflixRepository.mergeEntity(currentNetflix);
    }

    public void deleteNetflixRecord(long id){
        Netflix netflix = (Netflix) netflixRepository.findById(id);
        netflixRepository.removeEntity(netflix);
    }
    
}
