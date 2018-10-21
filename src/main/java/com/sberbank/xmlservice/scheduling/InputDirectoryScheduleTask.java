package com.sberbank.xmlservice.scheduling;

import com.sberbank.xmlservice.service.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class InputDirectoryScheduleTask {

    @Scheduled(fixedDelay = 10000)
    public void copyXmlFiles() {
        copyService.copyFiles();
    }

    @Autowired
    private CopyService copyService;
}
