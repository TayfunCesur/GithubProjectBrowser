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
        val client = createOkHttp(isDebugMode)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
        return retrofit.create(GithubService::class.java)
    }

    private fun createOkHttp(isDebugMode: Boolean): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        if (isDebugMode) {
            logging.level =
                HttpLoggingInterceptor.Level.BODY
        } else logging.level = HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }


}