package com.sberbank.xmlservice.service;

import com.sberbank.xmlservice.domain.Directory;

public interface DirectoryService {
    Directory getInputDirectory();
    Directory getOutputDirectory();
    Directory getArchiveDirectory();
    String[] getFilesFromDirectory(Directory directory);
}
