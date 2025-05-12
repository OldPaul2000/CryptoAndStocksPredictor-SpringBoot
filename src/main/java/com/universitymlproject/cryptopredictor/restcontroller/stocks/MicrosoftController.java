package com.universitymlproject.cryptopredictor.restcontroller.stocks;

import com.universitymlproject.cryptopredictor.dto.stocks.MicrosoftDTOGet;
import com.universitymlproject.cryptopredictor.dto.stocks.MicrosoftDTOPost;
import com.universitymlproject.cryptopredictor.service.stocksservice.MicrosoftService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks/microsoft")
public class MicrosoftController {

    private MicrosoftService microsoftService;

    public MicrosoftController(MicrosoftService microsoftService) {
        this.microsoftService = microsoftService;
    }

    @GetMapping("")
    public List<MicrosoftDTOGet> getMicrosoftHistory(@RequestParam("start") int start,
                                                     @RequestParam("resultsPerPage") int resultsPerPage){
        return microsoftService.getMicrosoftHistory(start, resultsPerPage);
    }

    @PostMapping("")
    public ResponseEntity<String> addNewMicrosoftRecord(@RequestBody MicrosoftDTOPost microsoftDTO) {
        microsoftService.addMicrosoftRecord(microsoftDTO);
        return new ResponseEntity<>("Added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMicrosoftRecord(@PathVariable long id,
                                                        @RequestBody MicrosoftDTOPost microsoftDTO) {
        microsoftService.updateMicrosoftRecord(id, microsoftDTO);
        return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMicrosoftRecord(@PathVariable long id) {
        microsoftService.deleteMicrosoftRecord(id);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }
    
}
