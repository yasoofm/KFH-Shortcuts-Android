package com.example.kfh_shortcuts.model

import com.example.kfh_shortcuts.network.ProductAPIService

class ProductRepo(private val api: ProductAPIService) {
    suspend fun getProductItems():List<Product> {
        return api.getProductItem()
    }

}