package com.sberbank.xmlservice.util;

import java.io.File;

public interface XmlFileUtilsTest {
    default String getAbsolutePath(String filePath) {
        var classLoader = getClass().getClassLoader();
        var file = new File(classLoader.getResource(filePath).getFile());
        return file.getAbsolutePath();
    }
}
