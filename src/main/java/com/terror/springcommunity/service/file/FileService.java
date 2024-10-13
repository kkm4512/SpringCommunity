package com.terror.springcommunity.service.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String upload(MultipartFile file, String path);
    String download(String path);
    boolean delete(String path);
    boolean update(MultipartFile file,String path);
}
