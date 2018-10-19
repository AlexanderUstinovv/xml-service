package com.sberbank.xmlservice.domain;

public class InputDirectory {

    public InputDirectory() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private long id;
    private String path;
}
