package com.sberbank.xmlservice.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileChecksumCounter implements CheckSumCounter {

    @Override
    public synchronized String getChecksum(String filePath, String algorithm) throws NoSuchAlgorithmException, IOException {
        var messageDigest = MessageDigest.getInstance(algorithm);
        var inputStream = Files.newInputStream(Paths.get(filePath));
        try (inputStream) {
            messageDigest.update(inputStream.readAllBytes());
            var hash = messageDigest.digest();
            var stringBuffer = new StringBuilder();
            for (byte aHash : hash) {
                if ((0xff & aHash) < 0x10) {
                    stringBuffer.append("0").append(Integer.toHexString((0xFF & aHash)));
                } else {
                    stringBuffer.append(Integer.toHexString(0xFF & aHash));
                }
            }
            return stringBuffer.toString();
        }
    }
}
