package com.sberbank.xmlservice.service;

import com.sberbank.xmlservice.domain.Directory;

import java.util.Date;

public interface DirectoryHistoryService {
    void createDirectoryHistory(Directory directory, String messageLog, Date date);
}
