package com.universitymlproject.cryptopredictor.restcontroller.crypto;

import com.universitymlproject.cryptopredictor.dto.crypto.RippleDTOGet;
import com.universitymlproject.cryptopredictor.dto.crypto.RippleDTOPost;
import com.universitymlproject.cryptopredictor.service.cryptoservice.RippleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cryptos/ripple")
public class RippleController {

    private RippleService rippleService;

    public RippleController(RippleService rippleService) {
        this.rippleService = rippleService;
    }

    @GetMapping("")
    public List<RippleDTOGet> getRippleHistory(@RequestParam("start") int start,
                                               @RequestParam("resultsPerPage") int resultsPerPage){
        return rippleService.getRippleHistory(start, resultsPerPage);
    }

    @PostMapping("")
    public ResponseEntity<String> addNewRippleRecord(@RequestBody RippleDTOPost rippleDTO) {
        rippleService.addRippleRecord(rippleDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRippleRecord(@PathVariable long id, RippleDTOPost rippleDTO) {
        rippleService.updateRippleRecord(id, rippleDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRippleRecord(@PathVariable long id) {
        rippleService.deleteRippleRecord(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
