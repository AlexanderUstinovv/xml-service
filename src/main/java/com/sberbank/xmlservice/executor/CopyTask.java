package com.sberbank.xmlservice.executor;

import com.sberbank.xmlservice.util.FileHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;

public class CopyTask implements Callable<Map<String, String>> {

    public CopyTask(String sourcePath, String destinationPath, FileHandler fileHandler) {
        this.destinationPath = destinationPath;
        this.fileHandler = fileHandler;
        this.sourcePath = sourcePath;
    }

    @Override
    public Map<String, String> call() {
        var isCopied = false;

        try {
            isCopied = fileHandler.copyFile(sourcePath, destinationPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Map.of("file", fileHandler.resolveCopiedFileName(
                sourcePath, destinationPath), "result", String.valueOf(isCopied));
    }

    private String sourcePath;
    private String destinationPath;
    private FileHandler fileHandler;
}
