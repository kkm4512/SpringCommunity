package com.terror.springcommunity.service.file;

import com.terror.springcommunity.constans.response.ApiResponseFileEnum;
import com.terror.springcommunity.exception.FileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static com.terror.springcommunity.constans.commonPath.GlobalPath.DELIMITER;


@Slf4j(topic = "FileGenerate")
@Component
public class FileGenerateUtil {
    public String generateFileName(MultipartFile file) {
        return UUID.randomUUID() + DELIMITER + file.getOriginalFilename();
    }

    public void copyFile(MultipartFile file, Path filePath) {
        try {
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream, filePath);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new FileException(ApiResponseFileEnum.FILE_UPLOAD_FAIL);
        }
    }
}
