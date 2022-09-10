package com.xing.postman.worker

import android.content.Context
import androidx.work.*
import com.xing.net.RetrofitUtil
import com.xing.net.proxy.ServiceProxy

/**
 * 网络测试框架
 * 测试类，以后要删掉的
 */
class TestConnectWorker(context: Context, workerParams: WorkerParameters) : Worker(context,
    workerParams
) {

    companion object {

        const val URL_KEY = "URL_KEY"
        const val RESULT_KEY = "RESULT_KEY"

        fun start(url: String) :WorkRequest {
            return OneTimeWorkRequestBuilder<TestConnectWorker>()
                .setInputData(Data.Builder().let {
                    it.putString(URL_KEY, url)
                    it.build()
                })
                .build()
        }
    }

    override fun doWork(): Result {

        inputData.getString(URL_KEY)?.let {
            RetrofitUtil.retrofit.create(ServiceProxy::class.java).doGetMethod(it, mapOf(Pair("sd", "ssss")))
        }?.let {
            return Result.success(Data.Builder().apply {
                putString(RESULT_KEY, it.execute().body())
            }.build())
        }

        return Result.failure()
    }
}