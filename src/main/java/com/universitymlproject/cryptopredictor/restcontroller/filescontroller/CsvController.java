package com.universitymlproject.cryptopredictor.restcontroller.filescontroller;

import com.universitymlproject.cryptopredictor.model.datafiles.CsvCurrency;
import com.universitymlproject.cryptopredictor.service.filesservice.CsvFileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/csv/data")
public class CsvController {

    private CsvFileService csvFileService;

    public CsvController(CsvFileService csvFileService) {
        this.csvFileService = csvFileService;
    }

    @GetMapping("")
    public List<CsvCurrency> getCryptoFromCsv(@RequestParam String fileName,
                                              @RequestParam LocalDate date){
        csvFileService.parseCsvData(fileName, date);
        return csvFileService.getData();
    }



}
