package com.example.furniturestore.model

import java.util.Date

data class Reminder(
    val id: Int,
    val time: Date,
    val type: String
)