package com.college.app.ui.schedule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.college.app.data.models.ClassSchedule
import com.college.app.databinding.ItemClassScheduleBinding

class ScheduleAdapter : ListAdapter<ClassSchedule, ScheduleAdapter.ScheduleViewHolder>(ScheduleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val binding = ItemClassScheduleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ScheduleViewHolder(
        private val binding: ItemClassScheduleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(classSchedule: ClassSchedule) {
            binding.apply {
                courseName.text = classSchedule.courseName
                courseTime.text = "${classSchedule.startTime} - ${classSchedule.endTime}"
                courseRoom.text = "Room: ${classSchedule.room}"
                courseType.text = classSchedule.type.capitalize()
                courseDay.text = classSchedule.day
            }
        }
    }

    private class ScheduleDiffCallback : DiffUtil.ItemCallback<ClassSchedule>() {
        override fun areItemsTheSame(oldItem: ClassSchedule, newItem: ClassSchedule): Boolean {
            return oldItem.courseId == newItem.courseId && 
                   oldItem.startTime == newItem.startTime &&
                   oldItem.day == newItem.day
        }

        override fun areContentsTheSame(oldItem: ClassSchedule, newItem: ClassSchedule): Boolean {
            return oldItem == newItem
        }
    }
} 