package com.example.furniturestore.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.furniturestore.common.enum.LoadStatus
import com.example.furniturestore.model.TaskModel
import com.example.furniturestore.repositories.MainLog
import com.example.furniturestore.repositories.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val log: MainLog?,
    val taskRepository: TaskRepository?
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadHomeApp()
    }

    fun loadHomeApp() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(status = LoadStatus.Loading())
            try {
                val fakeTasks = listOf(
                    TaskModel(title = "Do homework", description = "Complete math assignment before 9 PM"),
                    TaskModel(title = "Workout", description = "Go to the gym for 1 hour"),
                    TaskModel(title = "Buy groceries", description = "Milk, eggs, bread, and fruits"),
                    TaskModel(title = "Read book", description = "Read 30 pages of 'Atomic Habits'"),
                    TaskModel(title = "Clean room", description = "Vacuum and organize the desk")
                )
                _uiState.value = _uiState.value.copy(
                    listTask = fakeTasks,
                    status = LoadStatus.Success()
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(status = LoadStatus.Error(e.message ?: "Unknown error"))
            }
        }
    }

    fun addTask(title: String, description: String) {
        val newTask = TaskModel(title = title, description = description)
        val updatedList = _uiState.value.listTask.orEmpty() + newTask
        _uiState.value = _uiState.value.copy(listTask = updatedList)
    }
}

data class HomeUiState(
    val status: LoadStatus = LoadStatus.Innit(),
    val listTask: List<TaskModel>? = null
)