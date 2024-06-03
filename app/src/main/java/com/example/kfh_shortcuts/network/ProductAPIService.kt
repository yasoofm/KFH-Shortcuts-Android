package com.example.kfh_shortcuts.network

import android.graphics.Point
import com.example.kfh_shortcuts.model.Categorey
import com.example.kfh_shortcuts.model.ChatbotRequest
import com.example.kfh_shortcuts.model.Login
import com.example.kfh_shortcuts.model.Product
import com.example.kfh_shortcuts.model.ProductRequest
import com.example.kfh_shortcuts.model.RequestHistory
import com.example.kfh_shortcuts.model.response.ChatbotResponse
import com.example.kfh_shortcuts.model.response.Reward
import com.example.kfh_shortcuts.model.response.RewardPoints
import com.example.kfh_shortcuts.model.response.TokenResponse
import com.example.kfh_shortcuts.utiles.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductAPIService {
    @POST(Constants.loginEndpoint)
    suspend fun login(@Body user: Login): Response<TokenResponse>

    @GET(Constants.catalogEndpoint)
    suspend fun getCategory(
        @Header(Constants.authorization) token: String?,
    ): List<Categorey>

    @GET(Constants.productEndPoint)
    suspend fun getProductItem(
        @Header(Constants.authorization) token: String?,
        @Query("category") category: String): List<Product>

    @POST(Constants.productRequestEndPoint)
    suspend fun productRequest(
        @Header(Constants.authorization) token: String?,
        @Body request: ProductRequest
    ): Response<Void>

    @GET(Constants.rewardEndPoint)
    suspend fun getRewards(
        @Header(Constants.authorization) token: String?,
    ): List<Reward>

    @POST(Constants.rewardRequestEndPoints)
    suspend fun rewardRequest(
        @Header(Constants.authorization) token: String?,
        @Query("Id") id: Int

    ): Response<Void>


    @GET(Constants.RequestHistoryEndPoint)
    suspend fun getHistory(
        @Header(Constants.authorization) token: String?,
    ): Response<List<RequestHistory>>

    @GET(Constants.pointsEndPoint)
    suspend fun getPoints(
        @Header(Constants.authorization) token: String?,
    ): Response<RewardPoints>

    @POST(Constants.chatbotEndPoint)
    suspend fun chatbotmsg(
        @Header(Constants.authorization) token: String?,
        @Body request: ChatbotRequest

    ): Response<ChatbotResponse>
}