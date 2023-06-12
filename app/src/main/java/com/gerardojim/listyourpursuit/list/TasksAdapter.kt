package com.gerardojim.listyourpursuit.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gerardojim.listyourpursuit.databinding.ItemTaskBinding

class TasksAdapter : RecyclerView.Adapter<TasksAdapter.TasksItemViewHolder>() {
    private var items = emptyList<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksItemViewHolder =
        TasksItemViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TasksItemViewHolder, position: Int) =
        holder.bind(items[position])

    @SuppressLint("NotifyDataSetChanged")
    fun setTasks(items: List<Task>) {
        if (this.items != items) {
            this.items = items
            notifyDataSetChanged()
        }
    }

    inner class TasksItemViewHolder(private val taskItemBinding: ItemTaskBinding) :
        RecyclerView.ViewHolder(taskItemBinding.root) {

        fun bind(item: Task) {
            with(taskItemBinding) {
                taskEntryCheckbox.apply {
                    setOnCheckedChangeListener(null)
                    isChecked = item.isComplete
                    setOnCheckedChangeListener { _, isChecked ->
                        item.onIsCompleteClicked(isChecked)
                    }
                }
                taskEntryTitle.apply {
                    text = item.title
                    setOnClickListener {
                        item.onTitleClicked()
                    }
                }
            }
        }
    }
}
