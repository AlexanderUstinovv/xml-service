package com.sberbank.xmlservice.service;

import com.sberbank.xmlservice.integration.FileMessage;

public interface SendFileService {
    void sendFileToQueue(String queueName, FileMessage fileMessage);
}
