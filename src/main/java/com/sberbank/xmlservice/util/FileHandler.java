package com.sberbank.xmlservice.util;

import java.io.IOException;

public interface FileHandler {
    boolean copyFile(String sourceFile, String destination) throws IOException;
    String resolveCopiedFileName(String sourceFile, String destinationPath);
}
