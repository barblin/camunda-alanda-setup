package com.alanda.process.service.files.exceptions;

public class FileNotFoundExceptionCustom extends Exception {
    public FileNotFoundExceptionCustom(String errorMessage) {
        super(errorMessage);
    }
}