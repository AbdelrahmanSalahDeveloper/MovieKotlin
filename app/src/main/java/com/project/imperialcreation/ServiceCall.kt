package com.project.imperialcreation

import com.project.imperialcreation.moviekotlin.BuildConfig
import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object ServiceCall{
    val interceptor = HttpLoggingInterceptor()
    var client: OkHttpClient
    var retrofit: Retrofit
    val baseUrl ="http://api.themoviedb.org/3/movie/" // this is the base url for movie


    init {

        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        }
        client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(5, TimeUnit.MINUTES)
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .retryOnConnectionFailure(true)
                .build()
        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
    }

    val apiKey = "f03f5a763bef1453372728cf90975268"
}