package com.example.furniturestore.repositories.impl

import com.example.furniturestore.api.RetrofitInstance
import com.example.furniturestore.model.MainResponse
import com.example.furniturestore.model.Task
import com.example.furniturestore.repositories.MainLog
import com.example.furniturestore.repositories.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl@Inject constructor(
    private val log: MainLog?
) : TaskRepository {
    override suspend fun loadHome(): MainResponse? {
        return try {
            val data = RetrofitInstance.api.getHomeTasks()
            log?.d("get task", " ${data.isSuccess}")
            data
        } catch (e: Exception) {
            log?.e("error", "Error loading: ${e.message}")
            null
        }
    }

}