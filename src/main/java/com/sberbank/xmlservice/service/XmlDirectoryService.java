package com.sberbank.xmlservice.service;

import com.sberbank.xmlservice.dao.DirectoryMapper;
import com.sberbank.xmlservice.domain.Directory;
import com.sberbank.xmlservice.util.DirectoryChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class XmlDirectoryService implements DirectoryService {

    //TODO: breaks DRY principe
    @Override
    public Directory getInputDirectory() {
        var inputDirectory = directoryMapper.findInputDirectory();
        if (inputDirectory == null || inputDirectory.getPath().isEmpty()) {
            inputDirectory = new Directory() {{setPath(startInputDirectoryPath);setInput(true);setOutput(false);setArchive(false);}};
            directoryMapper.save(inputDirectory);
        }
        return inputDirectory;
    }

    @Override
    public Directory getOutputDirectory() {
        var outputDirectory = directoryMapper.findOutputDirectory();
        if (outputDirectory == null || outputDirectory.getPath().isEmpty()) {
            outputDirectory = new Directory() {{setPath(outputDirectoryPath);setInput(false);setOutput(true);setArchive(false);}};
            directoryMapper.save(outputDirectory);
        }
        return outputDirectory;
    }

    @Override
    public Directory getArchiveDirectory() {
        var archiveDirectory = directoryMapper.findOutputDirectory();
        if (archiveDirectory == null || archiveDirectory.getPath().isEmpty()) {
            archiveDirectory = new Directory() {{setPath(archiveDirectoryPath);setInput(false);setOutput(false);setArchive(true);}};
            directoryMapper.save(archiveDirectory);
        }
        return archiveDirectory;
    }

    @Override
    public String[] getFilesFromInputDirectory() {
        var inputDirectory = getInputDirectory();
        var filesInInput = directoryChecker.getFiles(inputDirectory.getPath());
        directoryHistoryService.createDirectoryHistory(inputDirectory,
                "Found files: " + filesInInput.length, new Date());
        return filesInInput;
    }

    @Value("${directories.start-input}")
    private String startInputDirectoryPath;
    @Value("${directories.output}")
    private String outputDirectoryPath;
    @Value("$directories.archive")
    private String archiveDirectoryPath;

    @Autowired
    private DirectoryMapper directoryMapper;
    @Autowired
    private DirectoryChecker directoryChecker;
    @Autowired
    private DirectoryHistoryService directoryHistoryService;
}
