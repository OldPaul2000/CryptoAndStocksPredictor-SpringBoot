package com.universitymlproject.cryptopredictor.restcontroller;

import com.universitymlproject.cryptopredictor.dto.crypto.BitcoinDTOGet;
import com.universitymlproject.cryptopredictor.dto.crypto.BitcoinDTOPost;
import com.universitymlproject.cryptopredictor.service.BitcoinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bitcoin")
public class BitcoinController {

    private BitcoinService bitcoinService;

    public BitcoinController(BitcoinService bitcoinService) {
        this.bitcoinService = bitcoinService;
    }

    @GetMapping("/history")
    public List<BitcoinDTOGet> getBitcoinHistory(@RequestParam("start") int start,
                                                 @RequestParam("resultsPerPage") int resultsPerPage){
        return bitcoinService.getBitcoinHistory(start, resultsPerPage);
    }

    @PostMapping("")
    public ResponseEntity<String> addNewBitcoinValue(@RequestBody BitcoinDTOPost bitcoinDTO) {
        bitcoinService.addNewBitcoinValues(bitcoinDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
