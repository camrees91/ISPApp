package com.isp.app.ui.programs.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.isp.app.R;
import com.isp.app.data.models.Program;
import java.util.List;

public class ProgramsAdapter extends RecyclerView.Adapter<ProgramsAdapter.ProgramViewHolder> {

    private final List<Program> programs;

    public ProgramsAdapter(List<Program> programs) {
        this.programs = programs;
    }

    @NonNull
    @Override
    public ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_program, parent, false);
        return new ProgramViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramViewHolder holder, int position) {
        Program program = programs.get(position);
        holder.programName.setText(program.getName());
    }

    @Override
    public int getItemCount() {
        return programs.size();
    }

    static class ProgramViewHolder extends RecyclerView.ViewHolder {
        TextView programName;

        ProgramViewHolder(@NonNull View itemView) {
            super(itemView);
            programName = itemView.findViewById(R.id.programName);
        }
    }
} 