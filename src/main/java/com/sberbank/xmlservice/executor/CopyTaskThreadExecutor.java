package com.sberbank.xmlservice.executor;

import java.util.List;
import java.util.Map;

public interface CopyTaskThreadExecutor {
    List<Map<String, String>> copyFiles(String[] fileList, String destination);
}
