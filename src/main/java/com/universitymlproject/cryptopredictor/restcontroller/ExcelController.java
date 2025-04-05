package com.universitymlproject.cryptopredictor.restcontroller;

import com.universitymlproject.cryptopredictor.model.excel.ExcelCurrency;
import com.universitymlproject.cryptopredictor.service.ExcelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/excel-data")
public class ExcelController {

    private ExcelService excelService;

    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @GetMapping("")
    public List<ExcelCurrency> getCryptoFromExcel(@RequestParam int start,
                                                  @RequestParam int resultsPerPage){
        List<ExcelCurrency> excelData = excelService.getExcelData("HistoricalData_Microsoft.xlsx", start, resultsPerPage);
        return excelData;
    }

}
