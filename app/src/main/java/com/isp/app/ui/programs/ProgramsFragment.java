package com.isp.app.ui.programs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.isp.app.R;

public class ProgramsFragment extends Fragment {

    private String[] programs = {"Computing Systems", "Biomedical Engineering", "Petroleum Engineering"};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_programs, container, false);

        ListView listView = view.findViewById(R.id.programs_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, programs);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((AdapterView<?> parent, View view1, int position, long id) -> {
            String selectedProgram = programs[position];
            Bundle bundle = new Bundle();
            bundle.putString("programId", selectedProgram);
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_programsFragment_to_coursesFragment, bundle);
        });

        return view;
    }
} 
