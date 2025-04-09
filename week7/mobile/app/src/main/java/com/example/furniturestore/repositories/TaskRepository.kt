package com.example.furniturestore.repositories

import com.example.furniturestore.model.TaskDao
import com.example.furniturestore.model.TaskModel
import com.example.furniturestore.model.toEntity
import com.example.furniturestore.model.toModel

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {
    fun getAllTasks(): Flow<List<TaskModel>> {
        return taskDao.getAllTasks().map { entities ->
            entities.map { it.toModel() }
        }
    }

    suspend fun addTask(task: TaskModel) {
        taskDao.insertTask(task.toEntity())
    }

    suspend fun deleteAllTasks() {
        taskDao.deleteAll()
    }
}