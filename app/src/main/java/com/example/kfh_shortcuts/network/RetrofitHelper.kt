package com.example.kfh_shortcuts.network

import android.provider.SyncStateContract
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(SyncStateContract.Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}