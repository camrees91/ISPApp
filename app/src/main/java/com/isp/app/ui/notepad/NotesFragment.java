package com.isp.app.ui.notepad;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.isp.app.R;
import com.isp.app.data.models.Note;
import java.util.List;

public class NotesFragment extends Fragment {

    private EditText editTextNote;
    private Button buttonSaveNote;
    private ListView listViewNotes;
    private NotesDatabase notesDatabase;
    private ArrayAdapter<Note> notesAdapter;
    private Note selectedNote;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notes, container, false);

        editTextNote = root.findViewById(R.id.editTextNote);
        buttonSaveNote = root.findViewById(R.id.buttonSaveNote);
        listViewNotes = root.findViewById(R.id.listViewNotes);

        notesDatabase = new NotesDatabase(requireContext());
        notesDatabase.insertNote("Sample Note");
        List<Note> allNotes = notesDatabase.getAllNotes();
        Log.d("NotesFragment", "Number of notes retrieved: " + allNotes.size());
        notesAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, allNotes);
        listViewNotes.setAdapter(notesAdapter);

        buttonSaveNote.setOnClickListener(v -> {
            String noteText = editTextNote.getText().toString().trim();
            if (!noteText.isEmpty()) {
                if (selectedNote != null) {
                    notesDatabase.updateNote(selectedNote.getId(), noteText);
                    selectedNote = null;
                } else {
                    notesDatabase.insertNote(noteText);
                }
                editTextNote.setText("");
                updateNoteList();
            } else {
                Toast.makeText(requireContext(), "Please enter a note.", Toast.LENGTH_SHORT).show();
            }
        });

        listViewNotes.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            selectedNote = notesAdapter.getItem(position);
            if (selectedNote != null) {
                editTextNote.setText(selectedNote.getText());
            }
        });

        listViewNotes.setOnItemLongClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Note noteToDelete = notesAdapter.getItem(position);
            if (noteToDelete != null) {
                notesDatabase.deleteNote(noteToDelete.getId());
                updateNoteList();
                Toast.makeText(requireContext(), "Note deleted.", Toast.LENGTH_SHORT).show();
            }
            return true;
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