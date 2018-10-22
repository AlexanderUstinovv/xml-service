package com.sberbank.xmlservice.scheduling;

import com.sberbank.xmlservice.service.DirectoryService;
import com.sberbank.xmlservice.service.HandleFileService;
import com.sberbank.xmlservice.util.XmlCopyFileHandler;
import com.sberbank.xmlservice.util.XmlMoveFileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class InputDirectoryScheduleTask {

    @Scheduled(fixedDelay = 10000)
    public void copyXmlFiles() {
        handleFileService.handleFiles(directoryService.getInputDirectory(),
                directoryService.getOutputDirectory(), new XmlCopyFileHandler());
    }

    @Scheduled(fixedDelay = 20000)
    public void moveXmlFiles() {
        handleFileService.handleFiles(directoryService.getOutputDirectory(),
                directoryService.getArchiveDirectory(), new XmlMoveFileHandler());
    }

    @Autowired
    private HandleFileService handleFileService;
    @Autowired
    private DirectoryService directoryService;
}
