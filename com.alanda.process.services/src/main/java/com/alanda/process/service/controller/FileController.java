package com.alanda.process.service.controller;

import com.alanda.process.service.api.models.ProcessFile;
import com.alanda.process.service.files.FileService;
import com.alanda.process.service.files.exceptions.FileNotFoundExceptionCustom;
import com.alanda.process.service.files.exceptions.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import static com.alanda.process.service.files.models.FileUploadRequest.of;

@Controller
public class FileController {

    private final FileService fileService;

    @Autowired
    FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/api/uploads/processes")
    public ResponseEntity<String> uploadProcess(@Valid @RequestBody ProcessFile process) {
        try {
            return ResponseEntity.ok(fileService.upload(of(process.getPath(), process.getName())));
        } catch (FileNotFoundExceptionCustom notFoundCustom) {
            return ResponseEntity.notFound().build();
        } catch (FileUploadException uploadException) {
            return ResponseEntity.badRequest().body(uploadException.getMessage());
        }
    }
}