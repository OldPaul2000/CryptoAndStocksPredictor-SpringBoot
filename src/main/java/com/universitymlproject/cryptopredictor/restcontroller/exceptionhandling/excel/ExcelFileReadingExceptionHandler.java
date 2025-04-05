package com.universitymlproject.cryptopredictor.restcontroller.exceptionhandling.excel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExcelFileReadingExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<FileReadingFailedResponse> handleFileReadingFailed(FileReadingFailedException exc){

        FileReadingFailedResponse response = new FileReadingFailedResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(exc.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
