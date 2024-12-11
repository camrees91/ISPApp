package com.isp.app.ui.contacts;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
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

        Button viewLocationButton = view.findViewById(R.id.viewLocationButton);
        viewLocationButton.setOnClickListener(v -> openMapLocation());

        Button goToSiteButton = view.findViewById(R.id.goToSiteButton);
        goToSiteButton.setOnClickListener(v -> openWebsite());

        Button callContactButton = view.findViewById(R.id.callContactButton);
        callContactButton.setOnClickListener(v -> makePhoneCall());

        Button emailContactButton = view.findViewById(R.id.emailContactButton);
        emailContactButton.setOnClickListener(v -> sendEmail());

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

    private void openMapLocation() {
        //Scranton, PA coordinates
        double latitude = 41.4106; // 41°24′38″N
        double longitude = -75.6675; // 75°40′03″W
        String label = "Contact Location";
        String uri = String.format("geo:%f,%f?q=%f,%f(%s)", latitude, longitude, latitude, longitude, Uri.encode(label));
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void openWebsite() {
        String url = "https://www.cna.nl.ca/";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        // Using createChooser to give users the option to choose their browser
        Intent chooser = Intent.createChooser(intent, "Open with");

        if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivity(chooser); // Show chooser dialog if an app can handle the intent
        } else {
            // Show error if no browser is available
            Toast.makeText(getContext(), "No browser available to open the link", Toast.LENGTH_SHORT).show();
        }
    }

    private void makePhoneCall() {
        if (contacts.isEmpty()) {
            Toast.makeText(getContext(), "No contacts available", Toast.LENGTH_SHORT).show();
            return;
        }
        String phoneNumber = contacts.get(0).getPhone(); // Example: use the first contact's phone number
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    private void sendEmail() {
        if (contacts.isEmpty()) {
            Toast.makeText(getContext(), "No contacts available", Toast.LENGTH_SHORT).show();
            return;
        }
        String email = contacts.get(0).getEmail(); // Example: use the first contact's email
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        intent.putExtra(Intent.EXTRA_TEXT, "Body of the email");
        if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }
} 