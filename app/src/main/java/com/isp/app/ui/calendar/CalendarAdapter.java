package com.isp.app.ui.calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.isp.app.R;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {

    private final List<String> calendarEvents;

    public CalendarAdapter(List<String> calendarEvents) {
        this.calendarEvents = calendarEvents;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendar_event, parent, false);
        return new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        String event = calendarEvents.get(position);
        holder.eventTitle.setText(event);
    }

    @Override
    public int getItemCount() {
        return calendarEvents.size();
    }

    static class CalendarViewHolder extends RecyclerView.ViewHolder {
        TextView eventTitle;

        CalendarViewHolder(@NonNull View itemView) {
            super(itemView);
            eventTitle = itemView.findViewById(R.id.eventTitle);
        }
    }
} 