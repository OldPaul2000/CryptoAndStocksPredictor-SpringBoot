package com.universitymlproject.cryptopredictor.restcontroller.exceptionhandling.excel;

public class FileReadingFailedException extends RuntimeException{

    public FileReadingFailedException(String message) {
        super(message);
    }

    public FileReadingFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileReadingFailedException(Throwable cause) {
        super(cause);
    }
}
