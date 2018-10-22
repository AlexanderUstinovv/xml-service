package com.sberbank.xmlservice.executor;

import com.sberbank.xmlservice.domain.File;
import com.sberbank.xmlservice.util.CheckSumCounter;
import com.sberbank.xmlservice.util.FileHandler;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class HandleFileTask implements Callable<Map<String, String>> {

    HandleFileTask(String sourcePath, ConcurrentHashMap<String, File> fileMap, String destinationPath, FileHandler fileHandler, CheckSumCounter checkSumCounter) {
        this.sourcePath = sourcePath;
        this.fileMap = fileMap;
        this.destinationPath = destinationPath;
        this.fileHandler = fileHandler;
        this.checkSumCounter = checkSumCounter;
    }

    @Override
    public Map<String, String> call() {
        var isCopied = false;
        String currentChecksum = "";
        try {
            currentChecksum = checkSumCounter.getChecksum(sourcePath, "MD5");
            if (!fileMap.containsKey(currentChecksum)) {
                isCopied = fileHandler.handleFile(sourcePath, destinationPath);
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return Map.of("file", fileHandler.resolveHandledFileName(
                sourcePath, destinationPath), "result", String.valueOf(isCopied),
                "checksum", currentChecksum);
    }

    private String sourcePath;
    private String destinationPath;
    private FileHandler fileHandler;
    private CheckSumCounter checkSumCounter;
    private ConcurrentHashMap<String, File> fileMap;
}
