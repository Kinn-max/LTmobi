package com.example.furniturestore.repositories

import com.example.furniturestore.model.TaskModel

interface TaskRepository {
    suspend fun loadHome(): List<TaskModel>?
}