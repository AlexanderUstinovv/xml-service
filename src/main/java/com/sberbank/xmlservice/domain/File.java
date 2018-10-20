package com.sberbank.xmlservice.domain;

import java.util.Date;

public class File {

    public File() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getMd5Sum() {
        return md5Sum;
    }

    public void setMd5Sum(byte[] md5Sum) {
        this.md5Sum = md5Sum;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private long id;
    private String name;
    private byte[] md5Sum;
    private byte[] content;
    private Date date;
}
