
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
    private static final String DATABASE_NAME = "notes.db"; // Database name
    private static final int DATABASE_VERSION = 1; // Database version

    public static final String TABLE_NOTES = "notes"; // Table name
    public static final String COLUMN_ID = "id"; // Column for note ID
    public static final String COLUMN_NOTE_TEXT = "note_text"; // Column for note text

    // Constructor
    public NotesDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // This method is called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create the notes table
        String CREATE_TABLE_NOTES = "CREATE TABLE " + TABLE_NOTES + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NOTE_TEXT + " TEXT NOT NULL)";
        db.execSQL(CREATE_TABLE_NOTES); // Execute the SQL statement
    }

    // This method is called when the database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);

        // Recreate the database
        onCreate(db);
    }

    // Insert a new note into the database
    public void insertNote(String noteText) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_TEXT, noteText); // Add the note text to the values

        db.insert(TABLE_NOTES, null, values); // Insert the note into the table
        db.close(); // Close the database
    }

    // Retrieve all notes from the database
    public List<Note> getAllNotes() {
        List<Note> notesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Query to select all rows from the notes table
        String SELECT_ALL_NOTES = "SELECT * FROM " + TABLE_NOTES;
        Cursor cursor = db.rawQuery(SELECT_ALL_NOTES, null);

        if (cursor.moveToFirst()) {
            do {
                // Get the note text from the cursor and add it to the list
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String noteText = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTE_TEXT));
                notesList.add(new Note(id, noteText));
            } while (cursor.moveToNext());
        }

        cursor.close(); // Close the cursor
        db.close(); // Close the database
        return notesList;
    }
}


