package com.terror.springcommunity.service.file;

import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FilePathUtil {
    public String getLastFilePath(String path) {
        return Paths.get(path).getFileName().toString();
    }

    public Path getFilePath(String path, String fileName) {
        return Paths.get(path, fileName);
    }
}
