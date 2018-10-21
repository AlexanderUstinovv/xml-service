package com.sberbank.xmlservice.service;

import com.sberbank.xmlservice.dao.DirectoryHistoryMapper;
import com.sberbank.xmlservice.domain.Directory;
import com.sberbank.xmlservice.domain.DirectoryHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class XmlDirectoryHistoryService implements DirectoryHistoryService {
    @Override
    public void createDirectoryHistory(Directory directory, String messageLog, Date date) {
        directoryHistoryMapper.save(new DirectoryHistory() {{
            setDirectoryId(directory.getId());
            setMessageLog(messageLog);
            setDate(date);
        }});
    }

    @Autowired
    private DirectoryHistoryMapper directoryHistoryMapper;
}
