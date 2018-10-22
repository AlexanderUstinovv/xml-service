package com.sberbank.xmlservice.service;

import com.sberbank.xmlservice.dao.DirectoryHistoryMapper;
import com.sberbank.xmlservice.domain.Directory;
import com.sberbank.xmlservice.domain.DirectoryHistory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
public class XmlDirectoryHistoryServiceTest {

    @Test
    public void createDirectoryHistory() {
        Mockito.doNothing().when(directoryHistoryMapper).save(Mockito.any(DirectoryHistory.class));
        sut.createDirectoryHistory(Mockito.mock(Directory.class), "", Mockito.mock(Date.class));
        Mockito.verify(directoryHistoryMapper).save(Mockito.any(DirectoryHistory.class));
    }

    @InjectMocks
    private XmlDirectoryHistoryService sut;
    @Mock
    private DirectoryHistoryMapper directoryHistoryMapper;

}
