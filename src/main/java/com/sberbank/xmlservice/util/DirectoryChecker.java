package com.sberbank.xmlservice.util;

import java.net.URI;

public interface DirectoryChecker {
    String[] getFiles(URI directoryPath);
}
