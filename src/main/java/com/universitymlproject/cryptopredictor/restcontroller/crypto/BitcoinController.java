package com.universitymlproject.cryptopredictor.restcontroller.crypto;

import com.universitymlproject.cryptopredictor.dto.crypto.BitcoinDTOGet;
import com.universitymlproject.cryptopredictor.dto.crypto.BitcoinDTOPost;
import com.universitymlproject.cryptopredictor.service.cryptoservice.BitcoinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cryptos/bitcoin")
public class BitcoinController {

    private BitcoinService bitcoinService;

    public BitcoinController(BitcoinService bitcoinService) {
        this.bitcoinService = bitcoinService;
    }

    @GetMapping("")
    public List<BitcoinDTOGet> getBitcoinHistory(@RequestParam("start") int start,
                                                 @RequestParam("resultsPerPage") int resultsPerPage){
        return bitcoinService.getBitcoinHistory(start, resultsPerPage);
    }

    @PostMapping("")
    public ResponseEntity<String> addNewBitcoinRecord(@RequestBody BitcoinDTOPost bitcoinDTO) {
        bitcoinService.addBitcoinRecord(bitcoinDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBitcoinRecord(@PathVariable long id, BitcoinDTOPost bitcoinDTO) {
        bitcoinService.updateBitcoinRecord(id, bitcoinDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBitcoinRecord(@PathVariable long id) {
        bitcoinService.deleteBitcoinRecord(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
