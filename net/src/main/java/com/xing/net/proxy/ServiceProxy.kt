package com.xing.net.proxy

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ServiceProxy {

    /**
     * 当选择使用这种方式时
     */
    @Streaming
    @Multipart
    @POST
    fun upload(
        @Url url: String,
        @PartMap requestBodyMap: Map<String, @JvmSuppressWildcards RequestBody>
    ): Call<String?>?

    @Multipart
    @POST
    fun upload(@Url url: String, @Part("files") files:MultipartBody.Part):Call<String?>?

    @Multipart
    @POST
    fun upload(
        @Url url: String,
        @Part("description") description: RequestBody,//添加描述
        @Part file: MultipartBody.Part
    ): Call<String?>?

    /**
     * @param url 要下载文件的目录
     */
    @Streaming
    @GET
    fun download(@Url url:String):Call<ResponseBody?>?

    /**
     * gei请求，通过指定的数据
     */
    @GET
    fun doGetMethod(@Url url:String, @QueryMap map:Map<String, String>):Call<String?>?

}