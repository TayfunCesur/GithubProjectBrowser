package com.tayfuncesur.remote.service

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object GithubServiceFactory {

    fun create(isDebugMode: Boolean): GithubService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(createOkHttp(isDebugMode))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
        return retrofit.create(GithubService::class.java)
    }

    private fun createOkHttp(isDebugMode: Boolean): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        if (isDebugMode) {
            val logging = HttpLoggingInterceptor()
            logging.level =
                HttpLoggingInterceptor.Level.BODY
            okHttpClient.interceptors().add(logging)
        }
        return okHttpClient
    }


}