package com.example.kfh_shortcuts.model.response

data class LoginResponse (
    var firstName: String,
    var lastName: String,
    var kfH_Id: Int,
    val token: String?
)