package com.universitymlproject.cryptopredictor.restcontroller.stocks;

import com.universitymlproject.cryptopredictor.dto.stocks.AmdDTOGet;
import com.universitymlproject.cryptopredictor.dto.stocks.AmdDTOPost;
import com.universitymlproject.cryptopredictor.service.stocksservice.AmdService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks/amd")
public class AmdController {

    private AmdService amdService;

    public AmdController(AmdService amdService) {
        this.amdService = amdService;
    }

    @GetMapping("")
    public List<AmdDTOGet> getAmdHistory(@RequestParam("start") int start,
                                         @RequestParam("resultsPerPage") int resultsPerPage){
        return amdService.getAmdHistory(start, resultsPerPage);
    }

    @PostMapping("")
    public ResponseEntity<String> addNewAmdRecord(@RequestBody AmdDTOPost amdDTO) {
        amdService.addAmdRecord(amdDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAmdRecord(@PathVariable long id, AmdDTOPost amdDTO) {
        amdService.updateAmdRecord(id, amdDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAmdRecord(@PathVariable long id) {
        amdService.deleteAmdRecord(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
