package com.universitymlproject.cryptopredictor.restcontroller.filescontroller;

import com.universitymlproject.cryptopredictor.model.datafiles.ExcelCurrency;
import com.universitymlproject.cryptopredictor.service.filesservice.ExcelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/excel/data")
public class ExcelController {

    private ExcelService excelService;

    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @GetMapping("")
    public List<ExcelCurrency> getCryptoFromExcel(@RequestParam String fileName,
                                                  @RequestParam LocalDate date){
        excelService.parseExcelData(fileName, date);
        return excelService.getData();
    }

}
