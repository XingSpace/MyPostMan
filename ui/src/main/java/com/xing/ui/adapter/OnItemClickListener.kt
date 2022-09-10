package com.xing.ui.adapter

interface OnItemClickListener<V:BaseVH<*>> {

    fun onItemClick(position: Int, v: V)

}