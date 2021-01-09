package com.alanda.process.service.files.data.models;

import java.io.File;
import java.util.Objects;

public final class ProcessFileDto {
    private final String name;
    private final File file;

    private ProcessFileDto(String name, File file) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(file);

        this.name = name;
        this.file = file;
    }

    public static ProcessFileDto of(String name, File file) {
        return new ProcessFileDto(name, file);
    }

    public String getName() {
        return name;
    }

    public File getFile() {
        return file;
    }
}
