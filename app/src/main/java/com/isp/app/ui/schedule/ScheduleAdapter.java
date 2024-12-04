package com.isp.app.ui.schedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.isp.app.R;
import com.isp.app.data.models.ScheduleItem;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {

    private final List<ScheduleItem> scheduleItems;

    public ScheduleAdapter(List<ScheduleItem> scheduleItems) {
        this.scheduleItems = scheduleItems;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        ScheduleItem item = scheduleItems.get(position);
        holder.scheduleTitle.setText(item.getTitle());
        holder.scheduleTime.setText(item.getTime());
    }

    @Override
    public int getItemCount() {
        return scheduleItems.size();
    }

    static class ScheduleViewHolder extends RecyclerView.ViewHolder {
        TextView scheduleTitle;
        TextView scheduleTime;

        ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            scheduleTitle = itemView.findViewById(R.id.scheduleTitle);
            scheduleTime = itemView.findViewById(R.id.scheduleTime);
        }
    }
} 