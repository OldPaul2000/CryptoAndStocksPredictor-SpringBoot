package com.universitymlproject.cryptopredictor.service.filesservice;

import com.universitymlproject.cryptopredictor.model.datafiles.CsvCurrency;
import com.universitymlproject.cryptopredictor.restcontroller.exceptionhandling.excel.FileReadingFailedException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.universitymlproject.cryptopredictor.restcontroller.exceptionhandling.ExceptionsMessages.CSV_READING_PROBLEM;

@Service
public class CsvFileService {

    @Value("${data.csv.location}")
    private String FILE_LOCATION;

    private List<CsvCurrency> data = new ArrayList<>();
    private boolean csvRecordDateEqualToMySQLDate = false;

    public void parseCsvData(String fileName, LocalDate lastRecordDateInMySQL){
        data = new ArrayList<>();
        if(fileName != null){
            Path filePath = Path.of(FILE_LOCATION + "\\" + fileName);
            try(CSVParser parser = CSVParser.parse(filePath, Charset.defaultCharset(), CSVFormat.DEFAULT)){
                Stream<CSVRecord> records = parser.stream();
                records.forEach(record -> {
                    insertRecordDataInList(record, lastRecordDateInMySQL);
                });
            }
            catch (IOException e){
                System.out.println(e.getMessage());
                throw new FileReadingFailedException(CSV_READING_PROBLEM);
            }
        }
        csvRecordDateEqualToMySQLDate = false;
    }

    private void insertRecordDataInList(CSVRecord record, LocalDate lastRecordDateInMySQL){
        if(record.getRecordNumber() > 1){
            LocalDate csvRecordDate = convertToLocalDate(record.get(0));
            if(csvRecordDate.isAfter(lastRecordDateInMySQL) && !csvRecordDateEqualToMySQLDate){
                data.add(0,convertToCsvCurrency(record));
            }
            else{
                csvRecordDateEqualToMySQLDate = true;
            }
        }
    }

    public List<CsvCurrency> getData(){
        return data;
    }

    private CsvCurrency convertToCsvCurrency(CSVRecord record){
        LocalDate date = convertToLocalDate(record.get(0));
        double close = convertToDouble(record.get(1));
        long volume = convertToLong(record.get(2));
        double open = convertToDouble(record.get(3));
        double high = convertToDouble(record.get(4));
        double low = convertToDouble(record.get(5));

        return new CsvCurrency(record.getRecordNumber() - 1, date, close, volume, open, high, low);
    }

    private LocalDate convertToLocalDate(String recordDate){
        String[] recordDateValues = recordDate.split("/");
        int month = Integer.parseInt(recordDateValues[0]);
        int day = Integer.parseInt(recordDateValues[1]);
        int year = Integer.parseInt(recordDateValues[2]);

        LocalDate date = LocalDate.of(year, month, day);
        return date;
    }

    private long convertToLong(String value){
        if(value.equalsIgnoreCase("N/A")){
            return -1;
        }
        return Long.parseLong(value);
    }

    private double convertToDouble(String value){
        return Double.parseDouble(value.replaceAll("[^\\d\\.]",""));
    }
}
