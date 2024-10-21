package com.terror.springcommunity.service.file;

import com.terror.springcommunity.constans.response.ApiResponseFileEnum;
import com.terror.springcommunity.exception.FileException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.terror.springcommunity.constans.commonPath.GlobalPath.*;


@Service
@Slf4j(topic = "FileServiceImpl")
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final DirectoryUtil directoryUtil;
    private final FileValidateUtil fileValidateUtil;
    private final FileGenerateUtil fileGenerateUtil;
    private final FilePathUtil filePathUtil;
    //회원가입 기능 구현 예시

    // 이미지를 저장하는 책임만 있음
    @Override
    public String upload(MultipartFile file, String path) {
        directoryUtil.mkdir(path);
        if (fileValidateUtil.validateFile(file) == null) {
            return null;
        }
        // username 추출
        String username = filePathUtil.getLastFilePath(path);
        log.info("username {}", username);
        String fileName = fileGenerateUtil.generateFileName(file);
        log.info("fileName {}", fileName);
        Path filePath = filePathUtil.getFilePath(path, fileName);
        fileGenerateUtil.copyFile(file, filePath);
        return PROFILE + SEPARATOR + username + SEPARATOR + fileName;
    }

    @Override
    public String download(String path) {
        Path filePath = Paths.get(path);
        String encodedPath = URLEncoder.encode(filePath.toString(), StandardCharsets.UTF_8)
                .replace("+", "%20")
                .replace("%2F", "/");
        return BASE_URL + SEPARATOR + encodedPath;
    }

    @Override
    public boolean delete(String path) {
        return false;
    }

    @Override
    public boolean update(MultipartFile file, String path) {
        return false;
    }
}
