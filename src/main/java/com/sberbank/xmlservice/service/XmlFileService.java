package com.sberbank.xmlservice.service;

import com.sberbank.xmlservice.dao.FileDirectoryLinkMapper;
import com.sberbank.xmlservice.dao.FileMapper;
import com.sberbank.xmlservice.domain.Directory;
import com.sberbank.xmlservice.domain.File;
import com.sberbank.xmlservice.domain.FileDirectoryLink;
import com.sberbank.xmlservice.integration.FileMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class XmlFileService implements FileService {

    @Override
    public Map<String, File> getMapFilesByDirectory(Directory directory) {
        return fileMapper.findMapFilesByDirectoryId(directory.getId());
    }

    @Override
    public File getFileByNameAndDirectory(String fileName, Directory directory) {
        return fileMapper.findByNameAndDirectoryId(fileName, directory.getId());
    }

    @Override
    public File getFileByCheckSum(String checkSum) {
        return fileMapper.findByMd5Sum(checkSum);
    }

    @Override
    public File createFile(String name, String checksum, byte[] content, Directory inputDirectory, Directory outputDirectory) {
        var file = new File() {{setName(name); setMd5Sum(checksum); setDate(new Date());}};
        fileMapper.save(file);
        var fileDirectoryLink = new FileDirectoryLink() {{
            setFileId(file.getId()); setDirectoryId(outputDirectory.getId());
        }};
        fileDirectoryLinkMapper.save(fileDirectoryLink);
        fileHistoryService.createFileHistory(
                file,
                String.format("File added to %s and copied to %s", inputDirectory.getPath(), outputDirectory.getPath()),
                new Date()
        );
        return file;
    }

    public void moveFileToDirectory(File file, Directory directory) {
        var fileDirectoryLink = fileDirectoryLinkMapper.findByFileId(file.getId());
        fileDirectoryLink.setDirectoryId(directory.getId());
        fileDirectoryLinkMapper.update(fileDirectoryLink);
        fileHistoryService.createFileHistory(
                file,
                String.format("File moved to %s", directory.getPath()),
                new Date()
        );
    }

    @Override
    public void sendFile(File file) {
        var fileMessafe = new FileMessage() {{
            setFileName(file.getName());
            setFileContent(file.getContent());
            setReceiver(receiverName);
            setSender(senderName);
            setDate(new Date());
        }};

        sendFileService.sendFileToQueue(fileMessafe);
    }

    @Override
    public void updateFile(File file, String message) {
        fileMapper.update(file);
        fileHistoryService.createFileHistory(file, message, new Date());
    }

    @Value("${spring.activemq.sender-name}")
    private String senderName;
    @Value("${spring.activemq.receiver-name}")
    private String receiverName;

    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private FileHistoryService fileHistoryService;
    @Autowired
    private SendFileService sendFileService;
    @Autowired
    private FileDirectoryLinkMapper fileDirectoryLinkMapper;
}
