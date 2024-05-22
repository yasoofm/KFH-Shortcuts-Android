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


class ProductViewModel : ViewModel()  {
    private val apiService = RetrofitHelper.getInstance().create(ProductAPIService::class.java)
    var token: TokenResponse? by mutableStateOf(null)

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = apiService.login(Login(username, password))
                token = response.body()
                println("login ${token?.token}")
            } catch (e: Exception) {
                println("Error $e")
            }
        }
    }
}

