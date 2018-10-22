package com.sberbank.xmlservice.util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class XmlDirectoryChecker implements DirectoryChecker {

    @Override
    public String[] getFiles(String directoryPath) {
        var directory = new File(directoryPath);
        var pathList = new ArrayList<String>();
        if(directory.isDirectory()) {
            var filesList = directory.listFiles(pathname -> pathname.getName().toLowerCase().endsWith(".xml")
                    || pathname.isDirectory());
            if (filesList != null && filesList.length > 0) {
                Arrays.stream(filesList).forEach(item -> pathList.add(item.getAbsolutePath()));
            }
        }
        return pathList.toArray(new String[0]);
    }
}
