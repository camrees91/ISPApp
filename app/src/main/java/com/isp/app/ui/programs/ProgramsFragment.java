package com.isp.app.ui.programs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.isp.app.R;
import com.isp.app.data.models.Program;
import com.isp.app.ui.programs.adapter.ProgramsAdapter;
import java.util.Arrays;
import java.util.List;

public class ProgramsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_programs, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.programsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Sample data for demonstration
        List<Program> programs = Arrays.asList(
            new Program("Computer Science", "CS101"),
            new Program("Business Administration", "BA202"),
            new Program("Mechanical Engineering", "ME303")
        );

        ProgramsAdapter adapter = new ProgramsAdapter(programs);
        recyclerView.setAdapter(adapter);

        return view;
    }
} 