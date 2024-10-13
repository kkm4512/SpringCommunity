package com.terror.springcommunity.service.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
public class FileValidateUtil {
    public MultipartFile validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        return file;
    }
}
