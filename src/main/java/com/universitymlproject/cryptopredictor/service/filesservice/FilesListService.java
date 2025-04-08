package com.universitymlproject.cryptopredictor.service.filesservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FilesListService {

    @Value("${data.csv.location}")
    private String CSV_FILES_DIRECTORY;

    @Value("${data.excel.location}")
    private String EXCEL_FILES_DIRECTORY;

    public List<String> getCsvTrainingDataFiles(){
        List<String> files = new ArrayList<>();
        try(Stream<Path> paths = Files.walk(Path.of(CSV_FILES_DIRECTORY), 1, FileVisitOption.FOLLOW_LINKS)){
            paths.forEach(path -> {
                files.add(path.getFileName().toString());
            });
            files.remove(0);
        }
        catch (IOException e){

        }
        return files;
    }

    public List<String> getExcelTrainingDataFiles(){
        List<String> files = new ArrayList<>();
        try(Stream<Path> paths = Files.walk(Path.of(EXCEL_FILES_DIRECTORY), 1, FileVisitOption.FOLLOW_LINKS)){
            paths.forEach(path -> {
                files.add(path.getFileName().toString());
            });
            files.remove(0);
        }
        catch (IOException e){

        }
        return files;
    }

}
