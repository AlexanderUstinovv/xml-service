package com.sberbank.xmlservice.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class FileChecksumCounter implements CheckSumCounter {

    @Override
    public byte[] getChecksum(String filePath, String algorithm) throws NoSuchAlgorithmException, IOException {
        var messageDigest = MessageDigest.getInstance(algorithm);
        var inputStream = Files.newInputStream(Paths.get(filePath));
        try(inputStream) {
            messageDigest.update(inputStream.readAllBytes());
            return messageDigest.digest();
        }
    }
}
