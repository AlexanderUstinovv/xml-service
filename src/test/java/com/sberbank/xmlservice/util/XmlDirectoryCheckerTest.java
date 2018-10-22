package com.sberbank.xmlservice.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;

public class XmlDirectoryCheckerTest implements XmlFileUtilsTest {

    @Before
    public void setUp() throws Exception {
        sut = new XmlDirectoryChecker();
    }

    @After
    public void tearDown() throws Exception {
        sut = null;
    }

    @Test
    public void getFiles() {
        String[] filePaths = sut.getFiles(getAbsolutePath(directoryPath));
        for (var item : filePaths ) {
            var result = Paths.get(getAbsolutePath(directoryPath)).resolve(item).toString().endsWith(fileExtention);
            Assert.assertTrue(result);
        }
    }

    private DirectoryChecker sut;
    private final String fileExtention = ".xml";
    private final String directoryPath = "files";
}
