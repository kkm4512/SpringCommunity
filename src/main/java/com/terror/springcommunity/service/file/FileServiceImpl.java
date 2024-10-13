package com.terror.springcommunity.service.file;

import com.terror.springcommunity.constans.response.ApiResponseFileEnum;
import com.terror.springcommunity.exception.FileException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;

import static com.terror.springcommunity.constans.commonPath.GlobalPath.PROFILES_PATH;
import static com.terror.springcommunity.constans.commonPath.GlobalPath.SEPARATOR;


@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final DirectoryUtil directoryUtil;
    private final FileValidateUtil fileValidateUtil;
    private final FileGenerateUtil fileGenerateUtil;
    private final FilePathUtil filePathUtil;

    // 이미지를 저장하는 책임만 있음
    @Override
    public String upload(MultipartFile file, String path) {
        directoryUtil.mkdir(path);
        if (fileValidateUtil.validateFile(file) == null) {
            return null;
        }
        // username 추출
        String username = filePathUtil.getLastFilePath(path);
        String fileName = fileGenerateUtil.generateFileName(file);
        Path filePath = filePathUtil.getFilePath(path, fileName);
        fileGenerateUtil.copyFile(file, filePath);
        return PROFILES_PATH + SEPARATOR + username + SEPARATOR + fileName;
    }

    @Override
    public String download(String path) {
        try {
            URL url = new URL(path);
            String filePath = url.getPath();
            System.out.println(filePath);
        } catch (IOException e) {
            throw new FileException(ApiResponseFileEnum.FILE_URL_FAIL);
        }

        return "";
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
