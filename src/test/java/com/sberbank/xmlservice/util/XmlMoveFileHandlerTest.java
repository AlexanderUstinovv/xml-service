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

public class XmlMoveFileHandlerTest implements XmlFileUtilsTest {
    @Before
    public void setUp() throws Exception {
        sut = new XmlMoveFileHandler();

        var path = Paths.get(getAbsolutePath(".").concat("/").concat(copyPath));
        copySoucePathObject = Paths.get(getAbsolutePath(".").concat("/").concat(copySourcePath));
        try {
            Files.createDirectory(path);
            Files.createDirectory(copySoucePathObject);
        } catch (FileAlreadyExistsException e) {}
        finally {
            var copyHandler = new XmlCopyFileHandler();
            var directoryChecker = new XmlDirectoryChecker();
            var sourceFiles = directoryChecker.getFiles(getAbsolutePath(sourcePath));
            for (var item : sourceFiles) {
                copyHandler.handleFile(Paths.get(getAbsolutePath(sourcePath)).resolve(item).toString(), copySoucePathObject.toString());
            }
            copyPathObject = Paths.get(getAbsolutePath("copy_files"));
        }
    }

    @After
    public void tearDown() throws Exception {
        recuresiveDelete(copyPathObject.toFile());
        copyPathObject = null;
        copySoucePathObject = null;
        sut = null;
    }

    @Test
    public void handleFileTest() throws IOException {

        var directoryChecker = new XmlDirectoryChecker();

        var sourceFiles = directoryChecker.getFiles(copySoucePathObject.toString());

        String[] fileList = directoryChecker.getFiles(copySoucePathObject.toString());

        for (var item : fileList) {
            sut.handleFile(copySoucePathObject.resolve(item).toString(), copyPathObject.toString());
        }

        var copyFiles = directoryChecker.getFiles(getAbsolutePath(copyPath));
        var result = false;
        for (var item : sourceFiles) {
            for (var copyItem : copyFiles) {
                result = Paths.get(item).getFileName().toString().equals(Paths.get(copyItem).getFileName().toString());
            }
        }
        var emptyDir = new File(copySoucePathObject.toString());
        Assert.assertTrue(result && emptyDir.listFiles().length == 0);
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
    private Path copySoucePathObject;
    private final String sourcePath = "files";
    private final String copySourcePath = "copy_source_files";
    private final String copyPath = "copy_files";
}
