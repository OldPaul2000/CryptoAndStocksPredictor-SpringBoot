package com.universitymlproject.cryptopredictor.restcontroller.stocks;

import com.universitymlproject.cryptopredictor.dto.stocks.AppleDTOGet;
import com.universitymlproject.cryptopredictor.dto.stocks.AppleDTOPost;
import com.universitymlproject.cryptopredictor.service.stocksservice.AppleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks/apple")
public class AppleController {

    private AppleService appleService;

    public AppleController(AppleService appleService) {
        this.appleService = appleService;
    }

    @GetMapping("")
    public List<AppleDTOGet> getAppleHistory(@RequestParam("start") int start,
                                             @RequestParam("resultsPerPage") int resultsPerPage){
        return appleService.getAppleHistory(start, resultsPerPage);
    }

    @PostMapping("")
    public ResponseEntity<String> addNewAppleRecord(@RequestBody AppleDTOPost appleDTO) {
        appleService.addAppleRecord(appleDTO);
        return new ResponseEntity<>("Added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAppleRecord(@PathVariable long id,
                                                    @RequestBody AppleDTOPost appleDTO) {
        appleService.updateAppleRecord(id, appleDTO);
        return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppleRecord(@PathVariable long id) {
        appleService.deleteAppleRecord(id);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }
    
}
