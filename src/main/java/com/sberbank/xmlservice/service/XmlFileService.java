package com.sberbank.xmlservice.service;

import com.sberbank.xmlservice.dao.FileDirectoryLinkMapper;
import com.sberbank.xmlservice.dao.FileMapper;
import com.sberbank.xmlservice.domain.Directory;
import com.sberbank.xmlservice.domain.File;
import com.sberbank.xmlservice.domain.FileDirectoryLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class XmlFileService implements FileService {

    @Override
    public Map<byte[], File> getMapFilesByDirectory(Directory directory) {
        return fileMapper.findMapFilesByDirectoryId(directory.getId());
    }

    @Override
    public File getFileByNameAndDirectory(String fileName, Directory directory) {
        return fileMapper.findByNameAndDirectoryId(fileName, directory.getId());
    }

    @Override
    public File createFile(String name, byte[] checksum, byte[] content, Directory inputDirectory, Directory outputDirectory) {
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

    @Override
    public void updateFile(File file) {
        fileMapper.update(file);
        fileHistoryService.createFileHistory(file, "File changed", new Date());
    }

    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private FileHistoryService fileHistoryService;
    @Autowired
    private FileDirectoryLinkMapper fileDirectoryLinkMapper;
}
