package com.universitymlproject.cryptopredictor.restcontroller.stocks;

import com.universitymlproject.cryptopredictor.dto.stocks.StarbucksDTOGet;
import com.universitymlproject.cryptopredictor.dto.stocks.StarbucksDTOPost;
import com.universitymlproject.cryptopredictor.service.stocksservice.StarbucksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks/starbucks")
public class StarbucksController {

    private StarbucksService starbucksService;

    public StarbucksController(StarbucksService starbucksService) {
        this.starbucksService = starbucksService;
    }

    @GetMapping("")
    public List<StarbucksDTOGet> getStarbucksHistory(@RequestParam("start") int start,
                                                     @RequestParam("resultsPerPage") int resultsPerPage){
        return starbucksService.getStarbucksHistory(start, resultsPerPage);
    }

    @PostMapping("")
    public ResponseEntity<String> addNewStarbucksRecord(@RequestBody StarbucksDTOPost starbucksDTO) {
        starbucksService.addStarbucksRecord(starbucksDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStarbucksRecord(@PathVariable long id, StarbucksDTOPost starbucksDTO) {
        starbucksService.updateStarbucksRecord(id, starbucksDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStarbucksRecord(@PathVariable long id) {
        starbucksService.deleteStarbucksRecord(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
