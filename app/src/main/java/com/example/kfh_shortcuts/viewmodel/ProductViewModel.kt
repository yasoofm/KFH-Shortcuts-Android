package com.example.kfh_shortcuts.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.kfh_shortcuts.model.Login
import com.example.kfh_shortcuts.model.response.TokenResponse
import com.example.kfh_shortcuts.network.ProductAPIService
import com.example.kfh_shortcuts.network.RetrofitHelper
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import com.example.kfh_shortcuts.model.Categorey
import com.example.kfh_shortcuts.model.Product
import com.example.kfh_shortcuts.model.ProductRequest
import com.example.kfh_shortcuts.model.response.LoginResponse


class ProductViewModel : ViewModel() {

    private val apiService = RetrofitHelper.getInstance().create(ProductAPIService::class.java)
    var token: TokenResponse? by mutableStateOf(null)
    var productItems: List<Product> by mutableStateOf(emptyList())
    var categories: List<Categorey> by mutableStateOf(emptyList())
        private set
    var selectedCategoryName: String? by mutableStateOf(null)
        private set
    var isLoggedIn: Boolean by mutableStateOf(false)

    var selectedProduct: Product? by mutableStateOf(null)
    var showValidationError: Boolean by mutableStateOf(true)


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
                val retrievedCategories = apiService.getCategory()
                categories = retrievedCategories
                val defaultCategorey = retrievedCategories.find { it.name == "Cards" }
                if (defaultCategorey != null) {
                    fetchProductsByCategory(defaultCategorey.name)
                }
            } catch (e: Exception) {
                println("Error $e")

            }
        }
    }

    fun fetchProductsByCategory(categoryName: String) {
        selectedCategoryName = categoryName
        viewModelScope.launch {
            try {
                val retrievedProductItems = apiService.getProductItem(categoryName)
                productItems = retrievedProductItems
            } catch (e: Exception) {
                println("Error $e")

            }
        }
    }

    fun productRequest(clientName: String, clientNumber: String) {
        viewModelScope.launch {
            try {
                if (selectedProduct != null)
                    apiService.productRequest(
                        token = token?.getBearerToken(),
                        ProductRequest(clientName, clientNumber, selectedProduct!!.id)
                    )

            } catch (e: Exception) {
                println("Error $e")
            }
        }
    }

//    fun rewardRequest(clientName: String, clientNumber: String) {
//        viewModelScope.launch {
//            try {
//                if (selectedProduct != null)
//                    apiService.rewardRequest(token = token?.getBearerToken(),
//                        rewardRequest(clientName, clientNumber, selectedProduct!!.id))
//
//            } catch (e: Exception) {
//                println("Error $e")
//            }
//        }
//    }


}

