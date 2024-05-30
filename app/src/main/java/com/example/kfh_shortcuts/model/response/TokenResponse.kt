package com.example.kfh_shortcuts.model.response

data class TokenResponse(val token: String, val firstName: String, val lastName: String, val kfH_Id: Int) {
    fun getBearerToken(): String {
        return "Bearer $token"
    }
}
