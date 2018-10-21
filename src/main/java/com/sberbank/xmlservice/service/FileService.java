package com.sberbank.xmlservice.service;

import com.sberbank.xmlservice.domain.Directory;
import com.sberbank.xmlservice.domain.File;

import java.util.Map;

public interface FileService {
    Map<String, File> getMapFilesByDirectory(Directory directory);
    File getFileByNameAndDirectory(String fileName, Directory directory);
    File createFile(String name, byte[] checksum, byte[] content, Directory inputDirectory, Directory outputDirectory);
    void updateFile(File file);
}
