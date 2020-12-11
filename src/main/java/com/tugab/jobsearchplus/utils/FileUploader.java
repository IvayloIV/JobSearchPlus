package com.tugab.jobsearchplus.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class FileUploader {

    private static final String BASE_PATH = "src/main/resources/static/";

    public String upload(String folderPath, MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return null;
        }

        final int buffSize = 1024;
        final byte[] buff = new byte[buffSize];
        int readBytes;
        String fullPath = new File(BASE_PATH + folderPath).getAbsolutePath();
        fullPath += File.separator + multipartFile.getOriginalFilename();

        try (BufferedInputStream bis = new BufferedInputStream(multipartFile.getInputStream());
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fullPath))) {
            while ((readBytes = bis.read(buff, 0, buffSize)) > 0) {
                bos.write(buff, 0, readBytes);
            }

            bos.flush();
            return multipartFile.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
