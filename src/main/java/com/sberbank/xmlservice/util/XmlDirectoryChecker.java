package com.sberbank.xmlservice.util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URI;

@Component
public class XmlDirectoryChecker implements DirectoryChecker {

    @Override
    public String[] getFiles(URI directoryPath) {
        var directory = new File(directoryPath);
        String[] directoryList = null;
        if(directory.isDirectory()) {
            directoryList = directory.list();
        }
        return directoryList;
    }
}
