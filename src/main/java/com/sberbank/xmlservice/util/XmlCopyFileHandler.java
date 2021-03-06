package com.sberbank.xmlservice.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class XmlCopyFileHandler implements FileHandler {
    @Override
    public synchronized boolean handleFile(String sourceFile, String destinationPath) throws IOException {
        var source = Paths.get(sourceFile);
        var copied = Paths.get(resolveHandledFileName(sourceFile, destinationPath));
        Files.copy(source, copied, StandardCopyOption.REPLACE_EXISTING);
        return Files.exists(copied) && Files.readAllLines(source).equals(Files.readAllLines(copied));
    }
}
