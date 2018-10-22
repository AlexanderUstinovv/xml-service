package com.sberbank.xmlservice.util;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface CheckSumCounter {
    String getChecksum(String filePath, String algorithm) throws NoSuchAlgorithmException, IOException;

}
