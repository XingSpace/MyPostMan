package com.xing.postman

import android.app.Application
import com.xing.ui.DisplayUtil

/**
 * application类，主要用于初始化很多资源库
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        DisplayUtil.init(this)
    }

}