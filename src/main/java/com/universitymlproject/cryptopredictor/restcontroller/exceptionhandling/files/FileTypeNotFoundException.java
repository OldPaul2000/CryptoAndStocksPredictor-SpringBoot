package com.universitymlproject.cryptopredictor.restcontroller.exceptionhandling.files;

public class FileTypeNotFoundException extends RuntimeException{

    public FileTypeNotFoundException(String message) {
        super(message);
    }

    public FileTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileTypeNotFoundException(Throwable cause) {
        super(cause);
    }

}
