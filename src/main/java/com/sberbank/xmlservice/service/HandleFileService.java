package com.sberbank.xmlservice.service;

import com.sberbank.xmlservice.domain.Directory;
import com.sberbank.xmlservice.util.FileHandler;

public interface HandleFileService {
    void handleFiles(Directory inputDirectory, Directory outputDirectory, FileHandler fileHandler);
}
