package com.example.furniturestore.repositories.impl

import com.example.furniturestore.api.RetrofitInstance
import com.example.furniturestore.model.Attachment
import com.example.furniturestore.model.MainResponse
import com.example.furniturestore.model.Reminder
import com.example.furniturestore.model.Subtask
import com.example.furniturestore.model.Task
import com.example.furniturestore.model.TaskModel
import com.example.furniturestore.repositories.MainLog
import com.example.furniturestore.repositories.TaskRepository
import java.time.LocalDateTime
import javax.inject.Inject

class TaskRepositoryImpl@Inject constructor(
    private val log: MainLog?
) : TaskRepository {
    override suspend fun loadHome(): List<TaskModel>? {
        return try {
//            val data = RetrofitInstance.api.getHomeTasks()
            val fakeTasks = listOf(
                TaskModel(
                    title = "Do homework",
                    description = "Complete math assignment before 9 PM"
                ),
                TaskModel(
                    title = "Workout",
                    description = "Go to the gym for 1 hour"
                ),
                TaskModel(
                    title = "Buy groceries",
                    description = "Milk, eggs, bread, and fruits"
                ),
                TaskModel(
                    title = "Read book",
                    description = "Read 30 pages of 'Atomic Habits'"
                ),
                TaskModel(
                    title = "Clean room",
                    description = "Vacuum and organize the desk"
                )
            )

            log?.d("get task", " hihi")
            fakeTasks
        } catch (e: Exception) {
            log?.e("error", "Error loading: ${e.message}")
            null
        }
    }

}