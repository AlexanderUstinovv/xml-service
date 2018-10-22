package com.sberbank.xmlservice.service;

import com.sberbank.xmlservice.integration.FileMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class XmlSendFileServiceTest {

    @Test
    public void sendFileToQueueTest() {
        Mockito.doNothing().when(jmsTemplate).convertAndSend(Mockito.any(String.class), Mockito.any(FileMessage.class));
        sut.sendFileToQueue("queue", Mockito.mock(FileMessage.class));
        Mockito.verify(jmsTemplate, Mockito.times(1)).convertAndSend(
                Mockito.any(String.class), Mockito.any(FileMessage.class)
        );
    }

    @Mock
    private JmsTemplate jmsTemplate;
    @InjectMocks
    private XmlSendFileService sut;
}
