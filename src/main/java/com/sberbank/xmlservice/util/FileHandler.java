package com.sberbank.xmlservice.util;

import java.io.IOException;

public interface FileHandler {
    boolean copyFile(String source, String destination) throws IOException;
}
