package com.isp.app.ui.programs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.isp.app.R;

public class CoursesFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_courses, container, false);

        TextView coursesTextView = view.findViewById(R.id.courses_text);
        String programId = getArguments() != null ? getArguments().getString("programId") : "Unknown Program";

        String coursesInfo = getCoursesInfo(programId);
        coursesTextView.setText(coursesInfo);

        return view;
    }

    private String getCoursesInfo(String programId) {
        switch (programId) {
            case "Computing Systems":
                return "Courses for Computing Systems:\n" +
                       "CS101: Introduction to Programming - Lecture: 3 hours, Lab: 2 hours\n" +
                       "CS201: Data Structures - Lecture: 3 hours, Lab: 1 hour\n" +
                       "CS301: Operating Systems - Lecture: 4 hours, Lab: 2 hours";
            case "Biomedical Engineering":
                return "Courses for Biomedical Engineering:\n" +
                       "BE101: Human Physiology - Lecture: 3 hours, Lab: 2 hours\n" +
                       "BE201: Biomaterials - Lecture: 3 hours, Lab: 1 hour\n" +
                       "BE301: Biomedical Instrumentation - Lecture: 4 hours, Lab: 2 hours";
            case "Petroleum Engineering":
                return "Courses for Petroleum Engineering:\n" +
                       "PE101: Introduction to Petroleum - Lecture: 3 hours, Lab: 2 hours\n" +
                       "PE201: Reservoir Engineering - Lecture: 3 hours, Lab: 1 hour\n" +
                       "PE301: Drilling Engineering - Lecture: 4 hours, Lab: 2 hours";
            default:
                return "No courses available for this program.";
        }
    }
} 