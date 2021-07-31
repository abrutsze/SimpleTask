package com.example.task.fragment.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.domian.model.DataInfo
import com.example.task.R
import com.example.task.base.adapter.BaseAdapter
import com.example.task.base.adapter.BaseViewHolder
import com.example.task.databinding.ItemDataBinding


class DataInfoListAdapter() :
    BaseAdapter<ViewBinding, DataInfo, BaseViewHolder<DataInfo, ViewBinding>>(
        EventsDiffCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<DataInfo, ViewBinding> {
        return ItemViewHolder(
            ItemDataBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class ItemViewHolder(private val binding: ItemDataBinding) :
        BaseViewHolder<DataInfo, ViewBinding>(binding) {
        override fun bind(item: DataInfo) {
            with(binding) {

                nameText.text = item.name

                itemView.context?.let {
                    Glide.with(it)
                        .load(item.url)
                        .circleCrop()
                        .placeholder(ContextCompat.getDrawable(it, R.drawable.ic_error_icon))
                        .into(image)
                }

                widthText.text =
                    item.width.toString()

                heightText.text =
                    item.height.toString()
            }
        }

    }

    private class EventsDiffCallback : DiffUtil.ItemCallback<DataInfo>() {
        override fun areItemsTheSame(oldItem: DataInfo, newItem: DataInfo): Boolean =
            oldItem.id == newItem.id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: DataInfo, newItem: DataInfo): Boolean =
            oldItem == newItem

    }

}


