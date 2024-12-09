package com.isp.app.ui.notepad;
import android.annotation.SuppressLint;
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
//import com.isp.app.notepad.NotesDatabase;
import com.isp.app.data.models.Note;

import java.util.List;

public class NotesFragment extends Fragment {

    private EditText editTextNote;
    private Button buttonSaveNote;
    private ListView listViewNotes;
    private NotesDatabase notesDatabase;
    private ArrayAdapter<Note> notesAdapter;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_notes, container, false);

        // Initialize views
        editTextNote = root.findViewById(R.id.editTextNote);
        buttonSaveNote = root.findViewById(R.id.buttonSaveNote);
        listViewNotes = root.findViewById(R.id.listViewNotes);

        // Set up the database and adapter
        notesDatabase = new NotesDatabase(requireContext());
        List<Note> allNotes = notesDatabase.getAllNotes();
        notesAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, allNotes);
        listViewNotes.setAdapter(notesAdapter);

        // Save button listener
        buttonSaveNote.setOnClickListener(v -> {
            String noteText = editTextNote.getText().toString().trim();
            if (!noteText.isEmpty()) {
                notesDatabase.insertNote(noteText); // Insert the note into the database
                editTextNote.setText(""); // Clear the input field
                updateNoteList(); // Refresh the ListView
            } else {
                Toast.makeText(requireContext(), "Please enter a note.", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    private void updateNoteList() {
        List<Note> allNotes = notesDatabase.getAllNotes();
        notesAdapter.clear();
        notesAdapter.addAll(allNotes);
        notesAdapter.notifyDataSetChanged();
    }
}

