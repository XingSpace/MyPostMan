package com.xing.postman.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * 用于处理get请求的worker
 * 尚未完成
 */
class GetRequestWorker(context: Context, workerParams: WorkerParameters) : Worker(
    context,
    workerParams
) {



    override fun doWork(): Result {
        TODO()
    }
}