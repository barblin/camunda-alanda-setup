package com.alanda.process.service.files;

import com.alanda.process.service.files.data.FileStore;
import com.alanda.process.service.files.data.models.ProcessFileDto;
import com.alanda.process.service.files.exceptions.FileNotFoundExceptionCustom;
import com.alanda.process.service.files.exceptions.FileUploadException;
import com.alanda.process.service.files.models.FileUploadRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

@Service
public class FileService {

    private final FileStore fileStore;

    @Autowired
    public FileService(FileStore fileStore) {
        this.fileStore = fileStore;
    }

    public String upload(FileUploadRequest request) throws FileNotFoundExceptionCustom, FileUploadException {
        Objects.requireNonNull(request);

        try {
            File upload = new File(request.getPath() + "//" + request.getFileName());
            return fileStore.upload(ProcessFileDto.of(request.getFileName(), upload));
        } catch (NullPointerException e) {
            throw new FileNotFoundExceptionCustom("File not found");
        }
    }
}
