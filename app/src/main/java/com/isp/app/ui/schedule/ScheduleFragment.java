package com.isp.app.ui.schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.isp.app.R;

public class ScheduleFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        TextView scheduleTextView = view.findViewById(R.id.schedule_text);

        // Mock schedule data for Computing Systems
        String scheduleInfo = "Schedule for Computing Systems:\n" +
                "Monday: CS101 - 9:00 AM to 11:00 AM\n" +
                "Tuesday: CS201 - 10:00 AM to 12:00 PM\n" +
                "Wednesday: CS301 - 1:00 PM to 3:00 PM\n" +
                "Thursday: CS101 Lab - 2:00 PM to 4:00 PM\n" +
                "Friday: CS201 Lab - 11:00 AM to 1:00 PM";

        scheduleTextView.setText(scheduleInfo);

        return view;
    }
} 