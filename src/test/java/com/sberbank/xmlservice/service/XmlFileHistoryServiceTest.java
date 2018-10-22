package com.sberbank.xmlservice.service;

import com.sberbank.xmlservice.dao.FileHistoryMapper;
import com.sberbank.xmlservice.domain.File;
import com.sberbank.xmlservice.domain.FileHistory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
public class XmlFileHistoryServiceTest {

    @Test
    public void createFileHistoryTest() {

        Mockito.doNothing().when(fileHistoryMapper).save(Mockito.any(FileHistory.class));
        sut.createFileHistory(Mockito.mock(File.class), "", Mockito.mock(Date.class));
        Mockito.verify(fileHistoryMapper).save(Mockito.any(FileHistory.class));
    }

    @Mock
    private FileHistoryMapper fileHistoryMapper;
    @InjectMocks
    private XmlFileHistoryService sut;
}
