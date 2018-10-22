package com.sberbank.xmlservice.service;

import com.sberbank.xmlservice.domain.Directory;
import com.sberbank.xmlservice.domain.File;

import java.util.Map;

public interface FileService {
    Map<String, File> getMapFilesByDirectory(Directory directory);
    File getFileByNameAndDirectory(String fileName, Directory directory);
    File getFileByCheckSum(String checkSum);
    File createFile(String name, String checksum, byte[] content, Directory inputDirectory, Directory outputDirectory);
    void updateFile(File file, String message);
    void moveFileToDirectory(File file, Directory directory);
    void sendFile(File file);
}
