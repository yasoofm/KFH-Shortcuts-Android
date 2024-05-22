package com.example.kfh_shortcuts.model.response

data class TokenResponse(val token: String?, val firstName: String, val lastName: String, val kfh_Id: Int) {
    fun getBearerToken(): String {
        return "Bearer $token"
    }
}
