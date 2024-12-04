package com.isp.app.data.models;

public class NewsArticle {
    private final String title;
    private final String content;

    public NewsArticle(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
} 