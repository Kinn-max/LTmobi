package com.example.furniturestore.repositories

import com.example.furniturestore.model.MainResponse
import com.example.furniturestore.model.Task

interface TaskRepository {
    suspend fun loadHome(): MainResponse?
}