package com.isp.app.data.models;

public class Note {
    private long id;
    private String text;

    public Note(long id, String text) {
        this.id = id;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
} 