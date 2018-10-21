package com.sberbank.xmlservice.service;

import com.sberbank.xmlservice.executor.XmlHandleFileTaskThreadExecutor;
import com.sberbank.xmlservice.util.XmlCopyFileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class XmlCopyService implements CopyService {

    public void copyFiles() {
        var inputDirectory = directoryService.getInputDirectory();
        var outputDirectory = directoryService.getOutputDirectory();
        var archiveDirectory = directoryService.getArchiveDirectory();
        var filesInInput = directoryService.getFilesFromInputDirectory();
        var savedFiles = new ConcurrentHashMap<>(fileService.getMapFilesByDirectory(archiveDirectory));

        var copyTaskThreadExecutor = new XmlHandleFileTaskThreadExecutor();
        var resultsFileList = copyTaskThreadExecutor.copyFiles(filesInInput, savedFiles,
                outputDirectory.getPath(), new XmlCopyFileHandler());
        if (resultsFileList != null) {
            for (var item : resultsFileList) {
                if (Boolean.valueOf(item.get("result"))) {
                    var filePath = item.get("file");
                    var fileChecksum = item.get("checksum").getBytes();
                    var fileName = Paths.get(filePath).getFileName().toString();
                    fileService.createFile(fileName, fileChecksum, null, inputDirectory, outputDirectory);
                }
            }
        }
    }

    @Autowired
    private DirectoryService directoryService;
    @Autowired
    private FileService fileService;
}
