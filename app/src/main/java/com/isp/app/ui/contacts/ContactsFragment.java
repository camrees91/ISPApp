package com.isp.app.ui.contacts;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.isp.app.R;
import com.isp.app.data.models.Contact;
import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends Fragment {

    private List<Contact> contacts = new ArrayList<>();
    private ContactsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.contactsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ContactsAdapter(contacts);
        recyclerView.setAdapter(adapter);

        Button createContactButton = view.findViewById(R.id.createContactButton);
        createContactButton.setOnClickListener(v -> showCreateContactDialog());

        return view;
    }

    private void showCreateContactDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_create_contact, null);
        builder.setView(dialogView);

        EditText nameInput = dialogView.findViewById(R.id.contactNameInput);
        EditText phoneInput = dialogView.findViewById(R.id.contactPhoneInput);
        EditText emailInput = dialogView.findViewById(R.id.contactEmailInput);

        builder.setPositiveButton("Create", (dialog, which) -> {
            String name = nameInput.getText().toString().trim();
            String phone = phoneInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();

            if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
                contacts.add(new Contact(name, phone, email));
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "Contact created", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }
} 