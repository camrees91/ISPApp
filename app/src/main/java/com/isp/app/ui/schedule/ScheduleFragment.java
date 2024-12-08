package com.isp.app.ui.schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.isp.app.R;
import java.util.Arrays;
import java.util.List;

public class ScheduleFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.scheduleRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Mock schedule data for Computing Systems
        List<ScheduleItem> scheduleItems = Arrays.asList(
                new ScheduleItem("CS101: Introduction to Programming", "Monday: 9:00 AM - 11:00 AM"),
                new ScheduleItem("CS201: Data Structures", "Tuesday: 10:00 AM - 12:00 PM"),
                new ScheduleItem("CS301: Operating Systems", "Wednesday: 1:00 PM - 3:00 PM"),
                new ScheduleItem("CS101 Lab", "Thursday: 2:00 PM - 4:00 PM"),
                new ScheduleItem("CS201 Lab", "Friday: 11:00 AM - 1:00 PM")
        );

        ScheduleAdapter adapter = new ScheduleAdapter(scheduleItems);
        recyclerView.setAdapter(adapter);

        return view;
    }
} 