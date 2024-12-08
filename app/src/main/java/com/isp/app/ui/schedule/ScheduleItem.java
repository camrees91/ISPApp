package com.isp.app.ui.schedule;

public class ScheduleItem {
    private final String courseName;
    private final String scheduleTime;

    public ScheduleItem(String courseName, String scheduleTime) {
        this.courseName = courseName;
        this.scheduleTime = scheduleTime;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }
} 