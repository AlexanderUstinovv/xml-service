package com.sberbank.xmlservice.integration;

import java.io.Serializable;
import java.util.Date;

public class FileMessage implements Serializable {

    public FileMessage() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "File{" + "from='" + sender + "', to='" +
                receiver + "', fileName='" + fileName + "'}";
    }

    private String sender;
    private String receiver;
    private String fileName;
    private byte[] fileContent;
    private Date date;
}
