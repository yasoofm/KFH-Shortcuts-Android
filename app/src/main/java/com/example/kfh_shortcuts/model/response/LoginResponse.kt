package com.example.kfh_shortcuts.model.response

class LoginResponse (
    var FirstName: String,
    var LastName: String,
    var KFHID: Int,
    val token: String?
)