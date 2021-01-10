package com.alanda.process.service.files.models;

public final class FileUploadRequest {
    private final String path;
    private final String fileName;

    private FileUploadRequest(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
    }

    public static FileUploadRequest of(String path, String fileName) {
        return new FileUploadRequest(path, fileName);
    }

    public String getPath() {
        return path;
    }

    public String getFileName() {
        return fileName;
    }
}