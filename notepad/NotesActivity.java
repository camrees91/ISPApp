package com.isp.app.ui.notepad;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast; // Optional for displaying messages

import com.isp.app.R;
import com.isp.app.data.models.Note;

import java.util.List;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.isp.app.R;
//import com.isp.app.data.NotesDatabase;
import com.isp.app.data.models.Note;

import java.util.List;




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
                    .replace(R.id.fragment_container, new NotesFragment()) // Replace with your fragment
                    .commit();
        }
    }
}

