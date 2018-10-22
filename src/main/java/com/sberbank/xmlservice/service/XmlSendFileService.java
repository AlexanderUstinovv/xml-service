package com.sberbank.xmlservice.service;

import com.sberbank.xmlservice.integration.FileMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class XmlSendFileService implements SendFileService {
    @Override
    public void sendFileToQueue(String queueName, FileMessage fileMessage) {
        jmsTemplate.convertAndSend(queueName, fileMessage);
    }

    @Autowired
    private JmsTemplate jmsTemplate;
}
