package com.example.myapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.entity.BusinessItem
import com.example.myapplication.databinding.ItemBusinessBinding
import com.example.myapplication.util.load

class BusinessAdapter(private val clickListener: (business: BusinessItem) -> Unit) :
    ListAdapter<BusinessItem, BusinessAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_business, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(position, itemCount, getItem(position), clickListener)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemBusinessBinding.bind(itemView)
        fun bind(
            position: Int,
            itemCount: Int,
            item: BusinessItem,
            clickListener: (business: BusinessItem) -> Unit
        ) = with(binding) {
            tvTitle.text = item.name
            tvDescription.text = item.alias
            tvPrice.text = item.price
            tvLocation.text = item.getLocation()
            ivBackground.load(item.imageUrl)
            tvRate.text = item.rating.toString()
            tvReviewCount.text = item.reviewCount.toString()
            decorationFirstItem.isVisible = position == 0
            decoration.isVisible = position > 0
            decorationLastItem.isVisible = position == itemCount - 1
            ivBackground.setOnClickListener { clickListener(item) }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<BusinessItem>() {
    override fun areItemsTheSame(oldItem: BusinessItem, newItem: BusinessItem): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: BusinessItem, newItem: BusinessItem): Boolean = oldItem == newItem
}
