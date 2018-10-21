package com.sberbank.xmlservice.executor;

import com.sberbank.xmlservice.domain.File;
import com.sberbank.xmlservice.util.FileHandler;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface HandleFileTaskThreadExecutor {
    List<Map<String, String>> copyFiles(String[] filesToCopy, ConcurrentHashMap<byte[], File> filesMap,
                                        String destination, FileHandler fileHandler);
}
