package com.isp.app.ui.schedule;

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
import com.isp.app.data.models.ScheduleItem;
import java.util.Arrays;
import java.util.List;

public class ScheduleFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.scheduleRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Updated data to match program names
        List<ScheduleItem> scheduleItems = Arrays.asList(
            new ScheduleItem("Computer Science - Algorithms", "09:00 AM - 10:00 AM"),
            new ScheduleItem("Business Administration - Marketing", "10:30 AM - 11:30 AM"),
            new ScheduleItem("Mechanical Engineering - Thermodynamics", "01:00 PM - 03:00 PM")
        );

        ScheduleAdapter adapter = new ScheduleAdapter(scheduleItems);
        recyclerView.setAdapter(adapter);

        return view;
    }
} 