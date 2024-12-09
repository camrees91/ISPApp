package com.isp.app.ui.notepad;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.isp.app.R;

public class NotesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        // Add the fragment if it's not already added
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new NotesFragment())
                    .commit();
        }
    }
} 