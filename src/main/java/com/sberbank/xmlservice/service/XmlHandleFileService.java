package com.sberbank.xmlservice.service;

import com.sberbank.xmlservice.domain.Directory;
import com.sberbank.xmlservice.executor.XmlHandleFileTaskThreadExecutor;
import com.sberbank.xmlservice.util.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class XmlHandleFileService implements HandleFileService {

    public void handleFiles(Directory inputDirectory, Directory outputDirectory, FileHandler fileHandler) {
        var archiveDirectory = directoryService.getArchiveDirectory();
        var filesInInput = directoryService.getFilesFromDirectory(inputDirectory);
        var savedFiles = new ConcurrentHashMap<>(fileService.getMapFilesByDirectory(archiveDirectory));
        var copyTaskThreadExecutor = new XmlHandleFileTaskThreadExecutor();

        var resultsFileList = copyTaskThreadExecutor.handleFiles(filesInInput, savedFiles,
                outputDirectory.getPath(), fileHandler);
        if (resultsFileList != null) {
            for (var item : resultsFileList) {
                if (Boolean.valueOf(item.get("result"))) {
                    var filePath = item.get("file");
                    var fileChecksum = item.get("checksum");
                    var fileName = Paths.get(filePath).getFileName().toString();
                    if (inputDirectory.isInput()) {
                        fileService.createFile(fileName, fileChecksum, null, inputDirectory, outputDirectory);
                    } else if (inputDirectory.isOutput()) {
                        var file = fileService.getFileByCheckSum(fileChecksum);
                        try {
                            file.setContent(Files.readAllBytes(Paths.get(filePath)));
                            fileService.updateFile(file, "Loaded file content");
                            fileService.moveFileToDirectory(file, outputDirectory);
                            fileService.sendFile(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    @Autowired
    private DirectoryService directoryService;
    @Autowired
    private FileService fileService;
}
