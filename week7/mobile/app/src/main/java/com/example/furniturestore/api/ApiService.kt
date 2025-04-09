package com.example.furniturestore.api


import com.example.furniturestore.model.MainResponse
import com.example.furniturestore.model.Task
import retrofit2.http.GET

interface ApiService {
    @GET("/api/researchUTH/tasks")
    suspend fun getHomeTasks(): MainResponse
}
