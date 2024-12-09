package com.isp.app.data.models;

public class Note {
    private String noteText;
    private long id;

    public Note(long id, String noteText) {
        this.id = id;
        this.noteText = noteText;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    @Override
    public String toString() {
        return noteText; // This defines how the Note appears in a ListView/RecyclerView
    }

}

