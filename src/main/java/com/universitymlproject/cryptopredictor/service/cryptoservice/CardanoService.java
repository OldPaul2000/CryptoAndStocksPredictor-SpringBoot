package com.universitymlproject.cryptopredictor.service.cryptoservice;

import com.universitymlproject.cryptopredictor.dto.crypto.CardanoDTOGet;
import com.universitymlproject.cryptopredictor.dto.crypto.CardanoDTOPost;
import com.universitymlproject.cryptopredictor.dtomappers.crypto.CryptoMapper;
import com.universitymlproject.cryptopredictor.model.crypto.Cardano;
import com.universitymlproject.cryptopredictor.repository.crypto.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardanoService {

    private CryptoRepository<Cardano> cardanoRepository;
    private CryptoMapper cryptoMapper;

    @Autowired
    public CardanoService(CryptoRepository<Cardano> cardanoRepository,
                          @Qualifier("cardanoMapper") CryptoMapper cryptoMapper) {
        this.cardanoRepository = cardanoRepository;
        this.cryptoMapper = cryptoMapper;
    }

    public List<CardanoDTOGet> getCardanoHistory(int start, int resultsPerPage){
        return cardanoRepository.getHistory(start, resultsPerPage)
                .stream().map(cardano -> {
                    return (CardanoDTOGet)cryptoMapper.toDTO(cardano);
                }).toList();
    }

    public void addCardanoRecord(CardanoDTOPost cardanoDTO){
        cardanoRepository.persistEntity((Cardano)cryptoMapper.toModel(cardanoDTO));
    }

    public void updateCardanoRecord(long id, CardanoDTOPost cardanoDTO){
        Cardano currentCardano = (Cardano) cardanoRepository.findById(id);
        currentCardano.setDate(cardanoDTO.getDate());
        currentCardano.setClose(cardanoDTO.getClose());
        currentCardano.setVolume(cardanoDTO.getVolume());
        currentCardano.setOpen(cardanoDTO.getOpen());
        currentCardano.setHigh(cardanoDTO.getHigh());
        currentCardano.setLow(cardanoDTO.getLow());

        cardanoRepository.mergeEntity(currentCardano);
    }

    public void deleteCardanoRecord(long id){
        Cardano cardano = (Cardano) cardanoRepository.findById(id);
        cardanoRepository.removeEntity(cardano);
    }
    
}
