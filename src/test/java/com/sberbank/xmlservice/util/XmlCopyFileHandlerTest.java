package com.sberbank.xmlservice.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XmlCopyFileHandlerTest implements XmlFileUtilsTest {

    @Before
    public void setUp() throws Exception {
        sut = new XmlCopyFileHandler();

        var path = Paths.get(getAbsolutePath(".").concat("/").concat(copyPath));
        try {
            Files.createDirectory(path);
        } catch (FileAlreadyExistsException e) {}
        finally {
            copyPathObject = Paths.get(getAbsolutePath("copy_files"));
        }
    }

    @After
    public void tearDown() throws Exception {
        recuresiveDelete(copyPathObject.toFile());
        copyPathObject = null;
        sut = null;
    }

    @Test
    public void handleFileTest() throws IOException {

        var directoryChecker = new XmlDirectoryChecker();

        String[] fileList = directoryChecker.getFiles(getAbsolutePath(sourcePath));

        for (var item : fileList) {
            sut.handleFile(Paths.get(getAbsolutePath(sourcePath)).resolve(item).toString(), copyPathObject.toString());
        }

        var sourceFiles = directoryChecker.getFiles(getAbsolutePath(sourcePath));
        var copyFiles = directoryChecker.getFiles(getAbsolutePath(copyPath));
        var result = false;
        for (var item : sourceFiles) {
            for (var copyItem : copyFiles) {
                result = Paths.get(item).getFileName().toString().equals(Paths.get(copyItem).getFileName().toString());
            }
        }
        Assert.assertTrue(result);
    }

    private boolean recuresiveDelete(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                recuresiveDelete(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    private FileHandler sut;
    private Path copyPathObject;
    private final String sourcePath = "files";
    private final String copyPath = "copy_files";
}
