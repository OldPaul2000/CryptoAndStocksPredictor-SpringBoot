package com.universitymlproject.cryptopredictor.restcontroller.stocks;

import com.universitymlproject.cryptopredictor.dto.stocks.AmazonDTOGet;
import com.universitymlproject.cryptopredictor.dto.stocks.AmazonDTOPost;
import com.universitymlproject.cryptopredictor.service.stocksservice.AmazonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks/amazon")
public class AmazonController {

    private AmazonService amazonService;

    public AmazonController(AmazonService amazonService) {
        this.amazonService = amazonService;
    }

    @GetMapping("")
    public List<AmazonDTOGet> getAmazonHistory(@RequestParam("start") int start,
                                               @RequestParam("resultsPerPage") int resultsPerPage){
        return amazonService.getAmazonHistory(start, resultsPerPage);
    }

    @PostMapping("")
    public ResponseEntity<String> addNewAmazonRecord(@RequestBody AmazonDTOPost amazonDTO) {
        amazonService.addAmazonRecord(amazonDTO);
        return new ResponseEntity<>("Added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAmazonRecord(@PathVariable long id,
                                                     @RequestBody AmazonDTOPost amazonDTO) {
        amazonService.updateAmazonRecord(id, amazonDTO);
        return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAmazonRecord(@PathVariable long id) {
        amazonService.deleteAmazonRecord(id);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }
    
}
