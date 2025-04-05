package com.universitymlproject.cryptopredictor.service;

import com.universitymlproject.cryptopredictor.model.excel.ExcelCurrency;
import com.universitymlproject.cryptopredictor.restcontroller.exceptionhandling.excel.FileReadingFailedException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.universitymlproject.cryptopredictor.restcontroller.exceptionhandling.ExceptionsMessages.EXCEL_READING_PROBLEM;

@Service
public class ExcelService {

    private final String FILE_LOCATION = "C:\\Users\\paulb\\Desktop\\Facultate\\Anul 3\\Sem 2\\Inteligenta artificiala\\Proiect\\Training Data";

    public List<ExcelCurrency> getExcelData(String fileName, int start, int resultsPerPage){
        List<ExcelCurrency> data = new ArrayList<>();

        String filePath = FILE_LOCATION + "\\" + fileName;
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath))){
            XSSFWorkbook workbook = new XSSFWorkbook(bis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow row = sheet.getRow(1);
            start = start == 0 ? ++start : start;
            for(int i = start; i < start + resultsPerPage; i++){
                row = sheet.getRow(i);
                if(row == null){
                    break;
                }
                ExcelCurrency rowData = convertToExcelCurrency(i, row);
                data.add(rowData);
            }
            return data;
        }
        catch (IOException e){
            throw new FileReadingFailedException(EXCEL_READING_PROBLEM);
        }
    }

    private ExcelCurrency convertToExcelCurrency(int index, XSSFRow row){
        String date = row.getCell(0).getStringCellValue();
        double close = convertToDouble(row.getCell(1));
        long volume = convertToLong(row.getCell(2));
        double open = convertToDouble(row.getCell(3));;
        double high = convertToDouble(row.getCell(4));;
        double low = convertToDouble(row.getCell(5));;

        return new ExcelCurrency(index, date, close, volume, open, high, low);
    }

    private long convertToLong(XSSFCell cell){
        String cellType = cell.getCellType().toString();
        if(cellType.equals("STRING")){
            return -1;
        }
        if(cellType.equals("NUMERIC")){
            return  (long) cell.getNumericCellValue();
        }
        return -1;
    }
    private double convertToDouble(XSSFCell cell){
        String cellType = cell.getCellType().toString();
        if(cellType.equals("STRING")){
            String value = cell.getStringCellValue();
            return Double.parseDouble(value.replaceAll("[^\\d\\.]",""));
        }
        if(cellType.equals("NUMERIC")){
            return cell.getNumericCellValue();
        }
        return -1;
    }

}
