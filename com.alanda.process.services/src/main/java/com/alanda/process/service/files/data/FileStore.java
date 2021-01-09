package com.alanda.process.service.files.data;

import com.alanda.process.service.files.data.models.ProcessFileDto;
import com.alanda.process.service.files.exceptions.FileUploadException;

public interface FileStore {

    String upload(ProcessFileDto processFileDto) throws FileUploadException;
}
