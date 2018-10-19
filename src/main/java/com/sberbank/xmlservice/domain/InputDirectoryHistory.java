package com.sberbank.xmlservice.domain;

import java.util.Date;

public class InputDirectoryHistory {

    public InputDirectoryHistory() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    public String getMessageLog() {
        return messageLog;
    }

    public void setMessageLog(String messageLog) {
        this.messageLog = messageLog;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private long id;
    private String directoryName;
    private String messageLog;
    private Date date;
}
