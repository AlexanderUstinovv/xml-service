package com.sberbank.xmlservice.domain;

import java.util.Date;

public class DirectoryHistory {

    public DirectoryHistory() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    private String messageLog;
    private Date date;

}
