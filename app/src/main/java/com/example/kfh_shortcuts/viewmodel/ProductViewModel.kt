package com.example.kfh_shortcuts.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.kfh_shortcuts.model.Login
import com.example.kfh_shortcuts.model.response.TokenResponse
import com.example.kfh_shortcuts.network.ProductAPIService
import com.example.kfh_shortcuts.network.RetrofitHelper
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import com.example.kfh_shortcuts.model.Categorey
import com.example.kfh_shortcuts.model.ChatbotRequest
import com.example.kfh_shortcuts.model.Product
import com.example.kfh_shortcuts.model.ProductRequest
import com.example.kfh_shortcuts.model.RequestHistory
import com.example.kfh_shortcuts.model.response.ChatbotResponse
import com.example.kfh_shortcuts.model.response.Reward
import com.example.kfh_shortcuts.model.response.RewardPoints


class ProductViewModel : ViewModel() {

    private val apiService = RetrofitHelper.getInstance().create(ProductAPIService::class.java)
    var token: TokenResponse? by mutableStateOf(null)
    var productItems: List<Product> by mutableStateOf(emptyList())
    var categories: List<Categorey> by mutableStateOf(emptyList())
        private set

    var history: List<RequestHistory> by mutableStateOf(emptyList())
        private set

    var earnedPoint: RewardPoints? by mutableStateOf(null)

    var rewards: List<Reward> by mutableStateOf(emptyList())
        private set
    var selectedCategoryName: String? by mutableStateOf(null)
    var selectedRewardName: String? by mutableStateOf(null)
        private set
    var isLoggedIn: Boolean by mutableStateOf(false)

    var selectedProduct: Product? by mutableStateOf(null)
    var showValidationError: Boolean by mutableStateOf(true)

    var messages by mutableStateOf<List<ChatbotResponse>>(emptyList())

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = apiService.login(Login(username, password))
                token = response.body()

                if (!response.isSuccessful) {
                    showValidationError = false;
                } else {
                    isLoggedIn = true
                }

            } catch (e: Exception) {
                println("Error $e")
            }
        }
    }


    fun fetchCategories() {
        viewModelScope.launch {
            try {
                val retrievedCategories = apiService.getCategory(
                    token = token?.getBearerToken()
                )
                categories = retrievedCategories
                val defaultCategorey = retrievedCategories[0]
                fetchProductsByCategory(defaultCategorey.name)
            } catch (e: Exception) {
                println("Error $e")

            }
        }
    }

    fun fetchProductsByCategory(categoryName: String) {
        selectedCategoryName = categoryName
        viewModelScope.launch {
            try {
                val retrievedProductItems = apiService.getProductItem(
                    token = token?.getBearerToken(),
                    categoryName
                )
                productItems = retrievedProductItems
            } catch (e: Exception) {
                println("Error $e")

            }
        }
    }

    fun productRequest(clientName: String, clientNumber: String) {
        viewModelScope.launch {
            try {
                if (selectedProduct != null) {
                    val response = apiService.productRequest(
                        token = token?.getBearerToken(),
                        ProductRequest(clientName, clientNumber, selectedProduct!!.id)
                    )
                    if(response.isSuccessful){
                        points()
                    }
                }

            } catch (e: Exception) {
                println("Error $e")
            }
        }
    }

    fun fetchRewards() {
        viewModelScope.launch {
            try {
                val retrievedRewards = apiService.getRewards(
                    token = token?.getBearerToken()
                )
                Log.e("REWARDS", retrievedRewards.first().toString())
                rewards = retrievedRewards

            } catch (e: Exception) {
                println("Error $e")

            }
        }
    }

    fun requestReward(id: Int) {
        viewModelScope.launch {
            try {
                val response = apiService.rewardRequest(
                    token = token?.getBearerToken(), id
                )
                if(response.isSuccessful){
                    points()
                }
            } catch (e: Exception) {
                println("Error $e")
            }
        }
    }


    fun requestHistory() {
        viewModelScope.launch {
            try {

                val retrievedHistory = apiService.getHistory(
                    token = token?.getBearerToken()
                )
                history = retrievedHistory.body()!!


            } catch (e: Exception) {
                println("Error $e")
            }
        }
    }

    fun points() {
        viewModelScope.launch {
            try {

                val retrievedPoints = apiService.getPoints(
                    token = token?.getBearerToken()
                )
                earnedPoint = retrievedPoints.body()!!


            } catch (e: Exception) {
                println("Error $e")
            }
        }
    }


    fun chat(request: ChatbotRequest) {
        viewModelScope.launch {
            try {
                val response = apiService.chatbotmsg(
                    token = token?.getBearerToken(),
                    request
                )
                println(response)
                if (response.isSuccessful){
                    val newList = ArrayList(messages)
                    newList.add(response.body())
                    response.body()?.let { messages = newList }

                }
            } catch (e: Exception) {
                println("Error $e")
            }
        }
    }

}

