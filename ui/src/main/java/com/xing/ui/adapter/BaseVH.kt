package com.xing.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseVH<T>(itemView: View, val viewType: Int) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(t: T)

}