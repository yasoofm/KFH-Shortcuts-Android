package com.example.kfh_shortcuts.model.response

data class TokenResponse(val token: String?) {
    fun getBearerToken(): String {
        return "Bearer $token"
    }
}
