package com.universitymlproject.cryptopredictor.restcontroller.stocks;

import com.universitymlproject.cryptopredictor.dto.stocks.TeslaDTOGet;
import com.universitymlproject.cryptopredictor.dto.stocks.TeslaDTOPost;
import com.universitymlproject.cryptopredictor.service.stocksservice.TeslaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks/tesla")
public class TeslaController {

    private TeslaService teslaService;

    public TeslaController(TeslaService teslaService) {
        this.teslaService = teslaService;
    }

    @GetMapping("")
    public List<TeslaDTOGet> getTeslaHistory(@RequestParam("start") int start,
                                             @RequestParam("resultsPerPage") int resultsPerPage){
        return teslaService.getTeslaHistory(start, resultsPerPage);
    }

    @PostMapping("")
    public ResponseEntity<String> addNewTeslaRecord(@RequestBody TeslaDTOPost teslaDTO) {
        teslaService.addTeslaRecord(teslaDTO);
        return new ResponseEntity<>("Added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTeslaRecord(@PathVariable long id,
                                                    @RequestBody TeslaDTOPost teslaDTO) {
        teslaService.updateTeslaRecord(id, teslaDTO);
        return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeslaRecord(@PathVariable long id) {
        teslaService.deleteTeslaRecord(id);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }
    
}
