package com.universitymlproject.cryptopredictor.service;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class ExcelReadingService {

    private final String FILE_LOCATION = "C:\\Users\\paulb\\Desktop\\Facultate\\Anul 3\\Sem 2\\Inteligenta artificiala\\Proiect\\Training Data";

    public void readExcelData(String fileName){
        String filePath = FILE_LOCATION + "\\" + fileName;
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath))){
            XSSFWorkbook workbook = new XSSFWorkbook(bis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow row;
            int rowIndex = 0;
            while ((row = sheet.getRow(rowIndex)) != null){
                XSSFCell cell;
                if(rowIndex == 0){
                    for(int i = 0; i < 6; i++){
                        cell = row.getCell(i);
                        System.out.print(cell.getStringCellValue() + "---");
                    }
                }
                else{
                    for(int i = 0; i < 6; i++){
                        cell = row.getCell(i);
                        if(i == 0){
                            System.out.print(cell.getRawValue() + "---");
                        }
                        if(i == 1){
                            System.out.print(cell.getRawValue() + "---");
                        }
                        if(i == 2){
                            System.out.print(cell.getRawValue() + "---");
                        }
                        if(i == 3){
                            System.out.print(cell.getRawValue() + "---");
                        }
                        if(i == 4){
                            System.out.print(cell.getRawValue() + "---");
                        }
                        if(i == 5){
                            System.out.print(cell.getRawValue() + "---");
                        }
                    }
                }
                System.out.println();
                rowIndex++;
            }
        }
        catch (IOException e){
            System.out.println("Error reading file");
        }



    }

}
