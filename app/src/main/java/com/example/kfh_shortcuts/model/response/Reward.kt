package com.example.kfh_shortcuts.model.response

import java.util.Date


data class Reward (
    var id: Int,
    var title: String,
    var requiredPoints: Int,
    var usages: Int,
    var dueDate: Date
)