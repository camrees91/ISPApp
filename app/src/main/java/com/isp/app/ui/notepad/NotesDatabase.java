package com.isp.app.ui.notepad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.isp.app.data.models.Note;
import java.util.ArrayList;
import java.util.List;

public class NotesDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NOTES = "notes";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOTE_TEXT = "note_text";

    public NotesDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_NOTES = "CREATE TABLE " + TABLE_NOTES + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NOTE_TEXT + " TEXT NOT NULL)";
        db.execSQL(CREATE_TABLE_NOTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }

    public void insertNote(String noteText) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_TEXT, noteText);
        db.insert(TABLE_NOTES, null, values);
        db.close();
    }

    public void updateNote(long id, String noteText) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_TEXT, noteText);
        db.update(TABLE_NOTES, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteNote(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTES, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<Note> getAllNotes() {
        List<Note> notesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String SELECT_ALL_NOTES = "SELECT * FROM " + TABLE_NOTES;
        Cursor cursor = db.rawQuery(SELECT_ALL_NOTES, null);

        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String noteText = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTE_TEXT));
                notesList.add(new Note(id, noteText));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return notesList;
    }
} 