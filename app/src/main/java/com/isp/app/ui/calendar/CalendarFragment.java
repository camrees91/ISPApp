package com.isp.app.ui.calendar;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.isp.app.R;
import com.kizitonwose.calendarview.CalendarView;
import com.kizitonwose.calendarview.model.CalendarDay;
import com.kizitonwose.calendarview.model.DayOwner;
import com.kizitonwose.calendarview.ui.DayBinder;
import com.kizitonwose.calendarview.ui.ViewContainer;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CalendarFragment extends Fragment {

    private Map<LocalDate, String> events = new HashMap<>();
    private YearMonth currentMonth;
    private TextView monthTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        monthTitle = view.findViewById(R.id.monthTitle);
        CalendarView calendarView = view.findViewById(R.id.calendarView);
        calendarView.setDayBinder(new DayBinder<DayViewContainer>() {
            @Override
            public DayViewContainer create(View view) {
                return new DayViewContainer(view);
            }

            @Override
            public void bind(DayViewContainer container, CalendarDay day) {
                container.dayText.setText(String.valueOf(day.getDate().getDayOfMonth()));
                if (day.getOwner() == DayOwner.THIS_MONTH) {
                    container.dayText.setVisibility(View.VISIBLE);
                    container.dayText.setOnClickListener(v -> {
                        String event = events.get(day.getDate());
                        if (event != null) {
                            Toast.makeText(getContext(), "Event: " + event, Toast.LENGTH_SHORT).show();
                        } else {
                            showCreateEventDialog(day.getDate());
                        }
                    });
                } else {
                    container.dayText.setVisibility(View.INVISIBLE);
                }
            }
        });

        currentMonth = YearMonth.now();
        updateMonthTitle();
        calendarView.setup(currentMonth.minusMonths(12), currentMonth.plusMonths(12), java.time.DayOfWeek.SUNDAY);
        calendarView.scrollToMonth(currentMonth);

        Button prevMonthButton = view.findViewById(R.id.prevMonthButton);
        Button nextMonthButton = view.findViewById(R.id.nextMonthButton);

        prevMonthButton.setOnClickListener(v -> {
            currentMonth = currentMonth.minusMonths(1);
            updateMonthTitle();
            calendarView.scrollToMonth(currentMonth);
        });

        nextMonthButton.setOnClickListener(v -> {
            currentMonth = currentMonth.plusMonths(1);
            updateMonthTitle();
            calendarView.scrollToMonth(currentMonth);
        });

        return view;
    }

    private void updateMonthTitle() {
        String monthName = currentMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
        monthTitle.setText(monthName + " " + currentMonth.getYear());
    }

    private void showCreateEventDialog(LocalDate date) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_create_event, null);
        builder.setView(dialogView);

        EditText eventNameInput = dialogView.findViewById(R.id.eventNameInput);
        EditText eventTimeInput = dialogView.findViewById(R.id.eventTimeInput);

        builder.setPositiveButton("Create", (dialog, which) -> {
            String eventName = eventNameInput.getText().toString().trim();
            String eventTime = eventTimeInput.getText().toString().trim();

            if (!eventName.isEmpty() && !eventTime.isEmpty()) {
                events.put(date, eventName + " at " + eventTime);
                Toast.makeText(getContext(), "Event created for " + date, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }

    private static class DayViewContainer extends ViewContainer {
        TextView dayText;

        DayViewContainer(View view) {
            super(view);
            dayText = view.findViewById(R.id.dayText);
        }
    }
} 