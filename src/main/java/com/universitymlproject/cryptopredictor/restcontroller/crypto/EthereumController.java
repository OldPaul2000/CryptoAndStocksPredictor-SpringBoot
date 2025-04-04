package com.universitymlproject.cryptopredictor.restcontroller.crypto;

import com.universitymlproject.cryptopredictor.dto.crypto.EthereumDTOGet;
import com.universitymlproject.cryptopredictor.dto.crypto.EthereumDTOPost;
import com.universitymlproject.cryptopredictor.service.cryptoservice.EthereumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cryptos/ethereum")
public class EthereumController {

    private EthereumService ethereumService;

    public EthereumController(EthereumService ethereumService) {
        this.ethereumService = ethereumService;
    }

    @GetMapping("")
    public List<EthereumDTOGet> getEthereumHistory(@RequestParam("start") int start,
                                                   @RequestParam("resultsPerPage") int resultsPerPage){
        return ethereumService.getEthereumHistory(start, resultsPerPage);
    }

    @PostMapping("")
    public ResponseEntity<String> addNewEthereumRecord(@RequestBody EthereumDTOPost ethereumDTO) {
        ethereumService.addEthereumRecord(ethereumDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEthereumRecord(@PathVariable long id, EthereumDTOPost ethereumDTO) {
        ethereumService.updateEthereumRecord(id, ethereumDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEthereumRecord(@PathVariable long id) {
        ethereumService.deleteEthereumRecord(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
