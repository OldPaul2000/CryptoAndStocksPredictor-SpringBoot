package com.universitymlproject.cryptopredictor.service.cryptoservice;

import com.universitymlproject.cryptopredictor.dto.crypto.RippleDTOGet;
import com.universitymlproject.cryptopredictor.dto.crypto.RippleDTOPost;
import com.universitymlproject.cryptopredictor.dtomappers.crypto.CryptoMapper;
import com.universitymlproject.cryptopredictor.model.crypto.Ripple;
import com.universitymlproject.cryptopredictor.repository.crypto.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RippleService {

    private CryptoRepository<Ripple> rippleRepository;
    private CryptoMapper<Ripple, RippleDTOGet, RippleDTOPost> cryptoMapper;

    @Autowired
    public RippleService(CryptoRepository<Ripple> rippleRepository,
                         @Qualifier("rippleMapper") CryptoMapper<Ripple, RippleDTOGet, RippleDTOPost> cryptoMapper) {
        this.rippleRepository = rippleRepository;
        this.cryptoMapper = cryptoMapper;
    }

    public List<RippleDTOGet> getRippleHistory(int start, int resultsPerPage){
        return rippleRepository.getHistory(start, resultsPerPage)
                .stream().map(ripple -> {
                    return (RippleDTOGet)cryptoMapper.toDTO(ripple);
                }).toList();
    }

    public void addRippleRecord(RippleDTOPost rippleDTO){
        rippleRepository.persistEntity((Ripple)cryptoMapper.toModel(rippleDTO));
    }

    public void updateRippleRecord(long id, RippleDTOPost rippleDTO){
        Ripple currentRipple = (Ripple) rippleRepository.findById(id);
        currentRipple.setDate(rippleDTO.getDate());
        currentRipple.setClose(rippleDTO.getClose());
        currentRipple.setVolume(rippleDTO.getVolume());
        currentRipple.setOpen(rippleDTO.getOpen());
        currentRipple.setHigh(rippleDTO.getHigh());
        currentRipple.setLow(rippleDTO.getLow());

        rippleRepository.mergeEntity(currentRipple);
    }

    public void deleteRippleRecord(long id){
        Ripple ripple = (Ripple) rippleRepository.findById(id);
        rippleRepository.removeEntity(ripple);
    }
    
}
