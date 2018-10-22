package com.sberbank.xmlservice.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class XmlMoveFileHandler implements FileHandler {
    @Override
    public synchronized boolean handleFile(String sourceFile, String destination) throws IOException {
        var source = Paths.get(sourceFile);
        var copied = Paths.get(resolveHandledFileName(sourceFile, destination));
        var sourceContent = Files.readAllLines(source);
        Files.move(source, copied, StandardCopyOption.REPLACE_EXISTING);
        return Files.exists(copied) && sourceContent.equals(Files.readAllLines(copied));
    }
}
