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
    private final OnProgramClickListener listener;

    public ProgramsAdapter(List<Program> programs, OnProgramClickListener listener) {
        this.programs = programs;
        this.listener = listener;
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
        holder.itemView.setOnClickListener(v -> listener.onProgramClick(program));
    }

    @Override
    public int getItemCount() {
        return programs.size();
    }

    public interface OnProgramClickListener {
        void onProgramClick(Program program);
    }

    static class ProgramViewHolder extends RecyclerView.ViewHolder {
        TextView programName;

        ProgramViewHolder(@NonNull View itemView) {
            super(itemView);
            programName = itemView.findViewById(R.id.programName);
        }
    }

    public void updatePrograms(List<Program> newPrograms) {
        programs.clear();
        programs.addAll(newPrograms);
        notifyDataSetChanged();
    }
} 