package com.xing.ui.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<D, T : BaseVH<D>>(var list: MutableList<D>) :
    RecyclerView.Adapter<T>() {

    var onItemClickListener:OnItemClickListener<T>? = null

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(position, holder)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}