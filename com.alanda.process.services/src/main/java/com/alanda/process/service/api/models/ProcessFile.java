package com.alanda.process.service.api.models;

import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotBlank;

public class ProcessFile {

    @NotBlank(message = "Path is mandatory")
    private String path;

    @NotBlank(message = "File name is mandatory")
    private String name;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
