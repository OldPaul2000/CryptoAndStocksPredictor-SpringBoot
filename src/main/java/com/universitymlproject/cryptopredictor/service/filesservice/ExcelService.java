package com.universitymlproject.cryptopredictor.service.filesservice;

import com.universitymlproject.cryptopredictor.model.datafiles.ExcelCurrency;
import com.universitymlproject.cryptopredictor.restcontroller.exceptionhandling.excel.FileReadingFailedException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.universitymlproject.cryptopredictor.restcontroller.exceptionhandling.ExceptionsMessages.EXCEL_READING_PROBLEM;

@Service
public class ExcelService {

    @Value("${data.excel.location}")
    private String FILE_LOCATION;

    List<ExcelCurrency> data = new ArrayList<>();
    private int rowIndex = 1;

    public void parseExcelData(String fileName, LocalDate lastRecordDateInMySQL){
        data = new ArrayList<>();
        if(fileName != null){
            String filePath = FILE_LOCATION + "\\" + fileName;
            try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath))){
                XSSFWorkbook workbook = new XSSFWorkbook(bis);
                XSSFSheet sheet = workbook.getSheetAt(0);
                insertRecordDataInList(sheet, lastRecordDateInMySQL);
            }
            catch (IOException e){
                throw new FileReadingFailedException(EXCEL_READING_PROBLEM);
            }
        }
        rowIndex = 1;
    }

    private void insertRecordDataInList(XSSFSheet sheet, LocalDate lastRecordDateInMySQL){
        XSSFRow row = sheet.getRow(rowIndex);
        LocalDate rowRecordDate;
        if(lastRecordDateInMySQL != null){
            rowRecordDate = convertToLocalDate(row.getCell(0).getStringCellValue());
            while(rowRecordDate.isAfter(lastRecordDateInMySQL)){
                ExcelCurrency rowData = convertToExcelCurrency(rowIndex, row);
                data.add(0, rowData);
                rowIndex++;
                row = sheet.getRow(rowIndex);
                rowRecordDate = convertToLocalDate(row.getCell(0).getStringCellValue());
            }
        }
        else{
            while((row = sheet.getRow(rowIndex)) != null){
                ExcelCurrency rowData = convertToExcelCurrency(rowIndex, row);
                data.add(0, rowData);
                rowIndex++;
            }
        }
    }

    private LocalDate convertToLocalDate(String recordDate){
        String[] recordDateValues = recordDate.split("/");
        int month = Integer.parseInt(recordDateValues[0]);
        int day = Integer.parseInt(recordDateValues[1]);
        int year = Integer.parseInt(recordDateValues[2]);

        LocalDate date = LocalDate.of(year, month, day);
        return date;
    }

    public List<ExcelCurrency> getData(){
        return data;
    }

    private ExcelCurrency convertToExcelCurrency(int index, XSSFRow row){
        LocalDate date = convertToLocalDate(row.getCell(0).getStringCellValue());
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
