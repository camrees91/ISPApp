package com.isp.app.data.models;

public class Program {
    private final String name;
    private final String id;

    public Program(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
} 