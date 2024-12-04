package com.isp.app.network;

import com.isp.app.data.models.Program;
import java.util.ArrayList;
import java.util.List;

public class ApiService {

    public List<Program> getPrograms() {
        // Simulate fetching data from a network or database
        List<Program> programs = new ArrayList<>();
        programs.add(new Program("Computer Science", "CS101"));
        programs.add(new Program("Business Administration", "BA202"));
        programs.add(new Program("Mechanical Engineering", "ME303"));
        return programs;
    }
} 