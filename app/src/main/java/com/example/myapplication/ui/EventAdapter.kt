package com.example.myapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.Event
import com.example.myapplication.databinding.ItemEventBinding
import com.example.myapplication.util.load

class EventAdapter(private val clickListener: (event: Event) -> Unit) :
    ListAdapter<Event, EventAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_event, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(position, itemCount, getItem(position), clickListener)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemEventBinding.bind(itemView)
        fun bind(
            position: Int,
            itemCount: Int,
            item: Event,
            clickListener: (event: Event) -> Unit
        ) = with(binding) {
            tvTitle.text = item.title
            tvDescription.text = item.description
            tvTime.text = item.getTimeStringByLocalTimeZone()
            tvLocation.text = item.getFullLocation()
            ivBackground.load(item.image)
            decorationFirstItem.isVisible = position == 0
            decoration.isVisible = position > 0
            decorationLastItem.isVisible = position == itemCount - 1
            ivBackground.setOnClickListener { clickListener(item) }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean = oldItem == newItem
}