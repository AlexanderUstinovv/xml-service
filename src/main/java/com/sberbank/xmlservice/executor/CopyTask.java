package com.sberbank.xmlservice.executor;

import com.sberbank.xmlservice.util.CheckSumCounter;
import com.sberbank.xmlservice.util.FileHandler;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Callable;

public class CopyTask implements Callable<Map<String, String>> {

    CopyTask(String sourcePath, byte[] checksum, String destinationPath, FileHandler fileHandler, CheckSumCounter checkSumCounter) {
        this.destinationPath = destinationPath;
        this.checksum = checksum;
        this.fileHandler = fileHandler;
        this.sourcePath = sourcePath;
        this.checkSumCounter = checkSumCounter;
    }

    @Override
    public Map<String, String> call() {
        var isCopied = false;
        byte[] currentChecksum = new byte[]{0};
        try {
            currentChecksum = checkSumCounter.getChecksum(sourcePath, "MD5");
            if (!Arrays.equals(checksum, currentChecksum)) {
                isCopied = fileHandler.copyFile(sourcePath, destinationPath);
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return Map.of("file", fileHandler.resolveCopiedFileName(
                sourcePath, destinationPath), "result", String.valueOf(isCopied),
                "checksum", Arrays.toString(currentChecksum));
    }

    private String sourcePath;
    private String destinationPath;
    private FileHandler fileHandler;
    private CheckSumCounter checkSumCounter;
    private byte[] checksum;
}
