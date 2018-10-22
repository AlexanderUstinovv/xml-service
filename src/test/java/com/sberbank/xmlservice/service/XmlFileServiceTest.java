package com.sberbank.xmlservice.service;

import com.sberbank.xmlservice.dao.FileDirectoryLinkMapper;
import com.sberbank.xmlservice.dao.FileMapper;
import com.sberbank.xmlservice.domain.Directory;
import com.sberbank.xmlservice.domain.File;
import com.sberbank.xmlservice.domain.FileDirectoryLink;
import com.sberbank.xmlservice.integration.FileMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Map;

@RunWith(SpringRunner.class)
public class XmlFileServiceTest {

    @Test
    public void getMapFilesByDirectoryTest() {
        Mockito.when(fileMapper.findMapFilesByDirectoryId(
                Mockito.any(Long.class))).thenReturn(Mockito.mock(Map.class));
        sut.getMapFilesByDirectory(Mockito.mock(Directory.class));
        Mockito.verify(fileMapper,
                Mockito.times(1)).findMapFilesByDirectoryId(Mockito.any(Long.class));
    }

    @Test
    public void getFileByNameAndDirectoryTest() {
        Mockito.when(fileMapper.findByNameAndDirectoryId(
                Mockito.any(String.class), Mockito.any(Long.class))).thenReturn(Mockito.mock(File.class));
        sut.getFileByNameAndDirectory("fileName", Mockito.mock(Directory.class));
        Mockito.verify(fileMapper, Mockito.times(1)).findByNameAndDirectoryId(
                Mockito.any(String.class), Mockito.any(Long.class));
    }

    @Test
    public void getFileByCheckSumTest() {
        Mockito.when(fileMapper.findByMd5Sum(Mockito.any(String.class))).thenReturn(Mockito.mock(File.class));
        sut.getFileByCheckSum("213123");
        Mockito.verify(fileMapper, Mockito.times(1)).findByMd5Sum(Mockito.any(String.class));
    }

    @Test
    public void createFileTest() {
        Mockito.doNothing().when(fileMapper).save(Mockito.any(File.class));
        Mockito.doNothing().when(fileDirectoryLinkMapper).save(Mockito.any(FileDirectoryLink.class));
        Mockito.doNothing().when(fileHistoryService).createFileHistory(
                Mockito.any(File.class), Mockito.any(String.class), Mockito.any(Date.class));
        sut.createFile("filename", "12312312", new byte[] {0},
                Mockito.mock(Directory.class), Mockito.mock(Directory.class));
        Mockito.verify(fileMapper, Mockito.times(1)).save(Mockito.any(File.class));
        Mockito.verify(fileDirectoryLinkMapper, Mockito.times(1)).save(
                Mockito.any(FileDirectoryLink.class));
        Mockito.verify(fileHistoryService, Mockito.times(1)).createFileHistory(
                Mockito.any(File.class), Mockito.any(String.class), Mockito.any(Date.class));
    }

    @Test
    public void moveFileToDirectoryTest() {
        Mockito.when(fileDirectoryLinkMapper.findByFileId(
                Mockito.any(Long.class))).thenReturn(Mockito.mock(FileDirectoryLink.class));
        Mockito.doNothing().when(fileDirectoryLinkMapper).update(
                Mockito.any(FileDirectoryLink.class));
        Mockito.doNothing().when(fileHistoryService).createFileHistory(
                Mockito.any(File.class), Mockito.any(String.class), Mockito.any(Date.class));
        sut.moveFileToDirectory(Mockito.mock(File.class), Mockito.mock(Directory.class));
        Mockito.verify(fileDirectoryLinkMapper,
                Mockito.times(1)).findByFileId(Mockito.any(Long.class));
        Mockito.verify(fileDirectoryLinkMapper,
                Mockito.times(1)).update(Mockito.any(FileDirectoryLink.class));
        Mockito.verify(fileHistoryService, Mockito.times(1)).createFileHistory(
                Mockito.any(File.class), Mockito.any(String.class), Mockito.any(Date.class));
    }

    @Test
    public void sendFileTest() {
        Mockito.doNothing().when(sendFileService).sendFileToQueue(
                Mockito.any(String.class), Mockito.any(FileMessage.class));
        sut.sendFile("queue", "sender", "receiver", Mockito.mock(File.class));
        Mockito.verify(sendFileService, Mockito.times(1)).sendFileToQueue(
                Mockito.any(String.class), Mockito.any(FileMessage.class));
    }

    @Mock
    private FileMapper fileMapper;
    @Mock
    private FileDirectoryLinkMapper fileDirectoryLinkMapper;
    @Mock
    private SendFileService sendFileService;
    @Mock
    private FileHistoryService fileHistoryService;
    @InjectMocks
    private XmlFileService sut;

}
