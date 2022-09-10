package com.xing.ui

import android.content.Context

val context: Context by lazy { _context!! }

var _context: Context? = null

object DisplayUtil {

    fun init(context: Context) {
        _context = context
    }

    fun dp2px(dip: Float):Int {
        val density: Float = context.resources.displayMetrics.density
        return (dip * density + 0.5f).toInt()
    }

    fun dp2px(dip: Int):Int {
        val density: Float = context.resources.displayMetrics.density
        return (dip * density + 0.5f).toInt()
    }

}