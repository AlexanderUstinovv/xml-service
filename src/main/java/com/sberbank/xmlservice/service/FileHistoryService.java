package com.sberbank.xmlservice.service;

import com.sberbank.xmlservice.domain.File;

import java.util.Date;

public interface FileHistoryService {
    void createFileHistory(File file, String messageLog, Date date);
}
