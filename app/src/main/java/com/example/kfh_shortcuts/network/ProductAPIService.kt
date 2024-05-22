package com.example.kfh_shortcuts.network

import com.example.kfh_shortcuts.model.Login
import com.example.kfh_shortcuts.model.response.LoginResponse
import com.example.kfh_shortcuts.model.response.TokenResponse
import com.example.kfh_shortcuts.utiles.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ProductAPIService {
    @POST(Constants.loginEndpoint)
    suspend fun login(@Body user: Login): Response<TokenResponse>

}