package com.college.app.ui.programs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.college.app.data.models.Program
import com.college.app.databinding.ItemProgramBinding

class ProgramsAdapter(
    private val onProgramClick: (Program) -> Unit
) : ListAdapter<Program, ProgramsAdapter.ProgramViewHolder>(ProgramDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramViewHolder {
        val binding = ItemProgramBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProgramViewHolder(binding, onProgramClick)
    }

    override fun onBindViewHolder(holder: ProgramViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ProgramViewHolder(
        private val binding: ItemProgramBinding,
        private val onProgramClick: (Program) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(program: Program) {
            binding.programName.text = program.name
            binding.root.setOnClickListener { onProgramClick(program) }
        }
    }

    private class ProgramDiffCallback : DiffUtil.ItemCallback<Program>() {
        override fun areItemsTheSame(oldItem: Program, newItem: Program): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Program, newItem: Program): Boolean {
            return oldItem == newItem
        }
    }
} 