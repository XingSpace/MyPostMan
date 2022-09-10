package com.xing.net

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitUtil {

    private val TAG by lazy { RetrofitUtil::class.java.simpleName }

    const val baseUrl = "http://www.baidu.com/"

    private val okHttpClient = OkHttpClient.Builder().run {
        connectTimeout(60, TimeUnit.SECONDS)
        readTimeout(60, TimeUnit.SECONDS)
        writeTimeout(60, TimeUnit.SECONDS)
        callTimeout(60, TimeUnit.SECONDS)
        addNetworkInterceptor(Interceptor { chain ->
            //将长连接改为一次性请求，防止资源浪费
            val request: Request =
                chain.request().newBuilder().addHeader("Connection", "close").build()
            chain.proceed(request)
        })
        build()
    }

    val retrofit: Retrofit = Retrofit.Builder().apply {
        baseUrl(baseUrl)
        client(okHttpClient)
        addConverterFactory(ScalarsConverterFactory.create())
        addConverterFactory(GsonConverterFactory.create())
    }.build()
}