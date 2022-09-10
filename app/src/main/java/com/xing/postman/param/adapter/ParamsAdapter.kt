package com.xing.postman.param.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.xing.postman.R
import com.xing.ui.adapter.BaseAdapter
import com.xing.ui.adapter.BaseVH
import com.xing.postman.param.data.Param
import com.xing.ui.DisplayUtil

/**
 * 用于处理首页的请求参数
 * 通过该adapter来实现增加或减少params的数量
 */
class ParamsAdapter(list: MutableList<Param>) : BaseAdapter<Param, ParamsVH>(list) {

    val ADD_TYPE: Int = 1
    val NORMAL_TYPE: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParamsVH {
        return ParamsVH(
            if (viewType == ADD_TYPE) {
                ImageView(parent.context).apply {
                    layoutParams = RecyclerView.LayoutParams(
                        RecyclerView.LayoutParams.MATCH_PARENT,
                        DisplayUtil.dp2px(32)
                    )
                    setImageResource(R.drawable.ic_baseline_add_24)
                }
            } else {
                LayoutInflater.from(parent.context).inflate(R.layout.item_param, parent, false)
            },
            viewType
        )
    }

    override fun onBindViewHolder(holder: ParamsVH, position: Int) {
        if (holder.viewType == ADD_TYPE) {
            holder.itemView.setOnClickListener {
                onItemClickListener?.onItemClick(position, holder)
            }
        } else {
            super.onBindViewHolder(holder, position)
            holder.clear.setOnClickListener {
                onItemClickListener?.onItemClick(position, holder)
            }
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position >= list.size) {
            ADD_TYPE
        } else {
            NORMAL_TYPE
        }
    }
}

class ParamsVH(v: View, type: Int) : BaseVH<Param>(v, type) {

    val key = v.findViewById<TextInputEditText>(R.id.key_input)
    val value = v.findViewById<TextInputEditText>(R.id.value_input)
    val clear = v.findViewById<ImageView>(R.id.clear)

    override fun bind(t: Param) {
        key.setText(t.key)
        value.setText(t.value)
    }
}