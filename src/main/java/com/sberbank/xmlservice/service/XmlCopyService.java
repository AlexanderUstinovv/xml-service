package com.sberbank.xmlservice.service;

import com.sberbank.xmlservice.executor.XmlCopyTaskThreadExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

@Service
public class XmlCopyService implements CopyService {

    public void copyFiles() {
        var inputDirectory = directoryService.getInputDirectory();
        var outputDirectory = directoryService.getOutputDirectory();
        var filesInInput = directoryService.getFilesFromInputDirectory();

        var savedFiles = fileService.getMapFilesByDirectory(outputDirectory);
        var filesToCopy = new HashMap<String, byte[]>();

        for (String item : filesInInput) {
            var checksum = new byte[]{0};
            var filename = Paths.get(item).getFileName().toString();
            if (savedFiles.containsKey(filename)) {
                checksum = savedFiles.get(filename).getMd5Sum();
            }
            filesToCopy.put(item, checksum);
        }

        var copyTaskThreadExecutor = new XmlCopyTaskThreadExecutor();
        var resultsFileList = copyTaskThreadExecutor.copyFiles(filesToCopy, outputDirectory.getPath());
        if (resultsFileList != null) {
            for (var item : resultsFileList) {
                var filePath = item.get("file");
                var fileChecksum = item.get("checksum").getBytes();
                var fileResult = item.get("result");
                var fileName = Paths.get(filePath).getFileName().toString();
                if (Boolean.valueOf(fileResult)) {
                    if (savedFiles.containsKey(fileName)) {
                        if (!Arrays.equals(savedFiles.get(fileName).getMd5Sum(), fileChecksum)) {
                            var fileToUpdate = fileService.getFileByNameAndDirectory(fileName, outputDirectory);
                            fileToUpdate.setMd5Sum(fileChecksum);
                            fileService.updateFile(fileToUpdate);
                        }
                    } else {
                        fileService.createFile(fileName, fileChecksum, null, inputDirectory, outputDirectory);
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
