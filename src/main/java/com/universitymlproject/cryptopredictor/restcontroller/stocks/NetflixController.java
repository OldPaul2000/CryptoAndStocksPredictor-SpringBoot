package com.universitymlproject.cryptopredictor.restcontroller.stocks;

import com.universitymlproject.cryptopredictor.dto.stocks.NetflixDTOGet;
import com.universitymlproject.cryptopredictor.dto.stocks.NetflixDTOPost;
import com.universitymlproject.cryptopredictor.service.stocksservice.NetflixService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks/netflix")
public class NetflixController {

    private NetflixService netflixService;

    public NetflixController(NetflixService netflixService) {
        this.netflixService = netflixService;
    }

    @GetMapping("")
    public List<NetflixDTOGet> getNetflixHistory(@RequestParam("start") int start,
                                                 @RequestParam("resultsPerPage") int resultsPerPage){
        return netflixService.getNetflixHistory(start, resultsPerPage);
    }

    @PostMapping("")
    public ResponseEntity<String> addNewNetflixRecord(@RequestBody NetflixDTOPost netflixDTO) {
        netflixService.addNetflixRecord(netflixDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateNetflixRecord(@PathVariable long id, NetflixDTOPost netflixDTO) {
        netflixService.updateNetflixRecord(id, netflixDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNetflixRecord(@PathVariable long id) {
        netflixService.deleteNetflixRecord(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
