package com.universitymlproject.cryptopredictor.restcontroller.dataupdatecontroller;

import com.universitymlproject.cryptopredictor.model.crypto.Crypto;
import com.universitymlproject.cryptopredictor.model.stocks.Stock;
import com.universitymlproject.cryptopredictor.service.databaseupdateservice.DatabaseUpdateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/data/update")
public class DataUpdateController {

    private DatabaseUpdateService updateService;

    public DataUpdateController(DatabaseUpdateService updateService) {
        this.updateService = updateService;
    }

    @GetMapping("/crypto")
    public List<Crypto> getNewCryptoData(@RequestParam String currency,
                                         @RequestParam String fileName){
        return updateService.getNewCryptoData(currency, fileName);
    }

    @GetMapping("/stock")
    public List<Stock> getNewStockData(@RequestParam String currency,
                                       @RequestParam String fileName){
        return updateService.getNewStockData(currency, fileName);
    }

    @PostMapping("/confirm")
    public void updateDatabase(){
        updateService.updateDatabase();
    }

    @PostMapping("/cancel")
    public void resetDataForUpdate(){
        updateService.resetData();
    }



}
