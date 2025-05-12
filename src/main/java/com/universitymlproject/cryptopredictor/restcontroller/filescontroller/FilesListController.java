package com.universitymlproject.cryptopredictor.restcontroller.filescontroller;

import com.universitymlproject.cryptopredictor.restcontroller.exceptionhandling.files.FileTypeNotFoundException;
import com.universitymlproject.cryptopredictor.service.filesservice.FilesListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.universitymlproject.cryptopredictor.restcontroller.exceptionhandling.ExceptionsMessages.FILE_TYPE_NOT_FOUND;


// Returns all files names for training data so the user can choose from which file
// to extract data for updating the MySQL database
@RestController
@RequestMapping("/api/v1/files/training-data")
public class FilesListController {

    private FilesListService filesListService;

    public FilesListController(FilesListService filesListService) {
        this.filesListService = filesListService;
    }

    @GetMapping("")
    private List<String> getAllFiles(@RequestParam String fileType){
        System.out.println("Get files");
        if(fileType.equalsIgnoreCase("csv")){
            return filesListService.getCsvTrainingDataFiles();
        }
        else if(fileType.equalsIgnoreCase("excel")){
            return filesListService.getExcelTrainingDataFiles();
        }
        else{
            throw new FileTypeNotFoundException(FILE_TYPE_NOT_FOUND);
        }
    }

}
