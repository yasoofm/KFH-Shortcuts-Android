package com.example.kfh_shortcuts.network

import com.example.kfh_shortcuts.utiles.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitHelper {
    fun getInstance(): Retrofit {
        val client: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .build()
        val gson = GsonBuilder().setDateFormat("yyy-MM-dd'T'HH:mm:ss").create()
        return Retrofit.Builder()
            .client(client)
            .baseUrl(Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

}

