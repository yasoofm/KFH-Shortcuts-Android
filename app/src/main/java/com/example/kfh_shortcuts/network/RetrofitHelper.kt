package com.example.kfh_shortcuts.network

import com.example.kfh_shortcuts.utiles.Constants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getInstance(): Retrofit {
        val gson = GsonBuilder().setDateFormat("yyy-MM-dd'T'HH:mm:ss").create()
        return Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

}

