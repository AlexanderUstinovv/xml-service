package com.sberbank.xmlservice.service;

import com.sberbank.xmlservice.domain.Directory;

public interface DirectoryService {
    Directory getInputDirectory();
    Directory getOutputDirectory();
    String[] getFilesFromInputDirectory();
}