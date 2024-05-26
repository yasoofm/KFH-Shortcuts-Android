package com.example.kfh_shortcuts.viewmodel

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
import com.example.kfh_shortcuts.model.Product
import com.example.kfh_shortcuts.model.response.LoginResponse


class ProductViewModel : ViewModel() {
    private val apiService = RetrofitHelper.getInstance().create(ProductAPIService::class.java)
    var token: TokenResponse? by mutableStateOf(null)
    var productItems: List<Product> by mutableStateOf(emptyList())


    fun login(username: String, password: String): Boolean {
        var result = false;
        viewModelScope.launch {
            try {
                val response = apiService.login(Login(username, password))
                println(response.message())
                println(response.code())
                token = response.body()
                result = true
            } catch (e: Exception) {
                println("Error $e")
            }
        }
        return result
    }




}

