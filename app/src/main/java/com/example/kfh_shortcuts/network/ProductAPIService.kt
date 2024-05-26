package com.example.kfh_shortcuts.network

import com.example.kfh_shortcuts.model.Categorey
import com.example.kfh_shortcuts.model.Login
import com.example.kfh_shortcuts.model.Product
import com.example.kfh_shortcuts.model.response.LoginResponse
import com.example.kfh_shortcuts.model.response.TokenResponse
import com.example.kfh_shortcuts.utiles.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductAPIService {
    @POST(Constants.loginEndpoint)
    suspend fun login(@Body user: Login): Response<TokenResponse>


    @GET (Constants.catalogEndpoint)
    suspend fun getCategory():List<Categorey>
    @GET(Constants.productEndPoint)
    suspend fun getProductItem(@Query("category") category: String):List<Product>

    
}