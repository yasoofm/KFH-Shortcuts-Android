package com.example.kfh_shortcuts.model.response

import java.util.Date

data class Reward (
    var title: String,
    var id: Int,
    var requiredPoints: Int,
    var usages: Int,
    var dueDate: Date
)