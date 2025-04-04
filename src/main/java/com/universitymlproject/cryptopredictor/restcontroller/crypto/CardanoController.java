package com.universitymlproject.cryptopredictor.restcontroller.crypto;

import com.universitymlproject.cryptopredictor.dto.crypto.CardanoDTOGet;
import com.universitymlproject.cryptopredictor.dto.crypto.CardanoDTOPost;
import com.universitymlproject.cryptopredictor.service.cryptoservice.CardanoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cryptos/cardano")
public class CardanoController {

    private CardanoService cardanoService;

    public CardanoController(CardanoService cardanoService) {
        this.cardanoService = cardanoService;
    }

    @GetMapping("")
    public List<CardanoDTOGet> getCardanoHistory(@RequestParam("start") int start,
                                                 @RequestParam("resultsPerPage") int resultsPerPage){
        return cardanoService.getCardanoHistory(start, resultsPerPage);
    }

    @PostMapping("")
    public ResponseEntity<String> addNewCardanoRecord(@RequestBody CardanoDTOPost cardanoDTO) {
        cardanoService.addCardanoRecord(cardanoDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCardanoRecord(@PathVariable long id, CardanoDTOPost cardanoDTO) {
        cardanoService.updateCardanoRecord(id, cardanoDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCardanoRecord(@PathVariable long id) {
        cardanoService.deleteCardanoRecord(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
