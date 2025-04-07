package com.example.furniturestore.model

data class MainResponse(
    val isSuccess: Boolean,
    val message: String,
    val data: List<Task>
)