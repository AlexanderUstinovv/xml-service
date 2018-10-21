package com.sberbank.xmlservice.util;

import java.io.IOException;
import java.nio.file.Paths;

public interface FileHandler {
    boolean handleFile(String sourceFile, String destination) throws IOException;

    default String resolveHandledFileName(String sourceFile, String destinationPath) {
        var source = Paths.get(sourceFile);
        return Paths.get(destinationPath).resolve(source.getFileName()).toString();
    }
}
