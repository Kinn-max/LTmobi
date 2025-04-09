package com.example.furniturestore.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String
)

fun TaskEntity.toModel() = TaskModel(
    title = title,
    description = description
)

fun TaskModel.toEntity() = TaskEntity(
    title = title,
    description = description
)