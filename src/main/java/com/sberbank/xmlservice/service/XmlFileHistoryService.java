package com.sberbank.xmlservice.service;

import com.sberbank.xmlservice.dao.FileHistoryMapper;
import com.sberbank.xmlservice.domain.File;
import com.sberbank.xmlservice.domain.FileHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class XmlFileHistoryService implements FileHistoryService {
    @Override
    public void createFileHistory(File file, String messageLog, Date date) {
        fileHistoryMapper.save(new FileHistory(){{
            setFileId(file.getId());
            setMessageLog(messageLog);
            setDate(date);
        }});
    }

    @Autowired
    private FileHistoryMapper fileHistoryMapper;
}
