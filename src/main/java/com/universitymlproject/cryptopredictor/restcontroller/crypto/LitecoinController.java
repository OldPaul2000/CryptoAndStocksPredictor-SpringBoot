package com.universitymlproject.cryptopredictor.restcontroller.crypto;

import com.universitymlproject.cryptopredictor.dto.crypto.LitecoinDTOGet;
import com.universitymlproject.cryptopredictor.dto.crypto.LitecoinDTOPost;
import com.universitymlproject.cryptopredictor.service.cryptoservice.LitecoinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cryptos/litecoin")
public class LitecoinController {

    private LitecoinService litecoinService;

    public LitecoinController(LitecoinService litecoinService) {
        this.litecoinService = litecoinService;
    }

    @GetMapping("")
    public List<LitecoinDTOGet> getLitecoinHistory(@RequestParam("start") int start,
                                                   @RequestParam("resultsPerPage") int resultsPerPage){
        return litecoinService.getLitecoinHistory(start, resultsPerPage);
    }

    @PostMapping("")
    public ResponseEntity<String> addNewLitecoinRecord(@RequestBody LitecoinDTOPost litecoinDTO) {
        litecoinService.addLitecoinRecord(litecoinDTO);
        return new ResponseEntity<>("Added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateLitecoinRecord(@PathVariable long id,
                                                       @RequestBody LitecoinDTOPost litecoinDTO) {
        litecoinService.updateLitecoinRecord(id, litecoinDTO);
        return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLitecoinRecord(@PathVariable long id) {
        litecoinService.deleteLitecoinRecord(id);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }
    
}

