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
    private val taskRepository: TaskRepository
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
                taskRepository.getAllTasks().collect { tasks ->
                    _uiState.value = _uiState.value.copy(
                        listTask = tasks,
                        status = LoadStatus.Success()
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    status = LoadStatus.Error(e.message ?: "Unknown error")
                )
            }
        }
    }

    fun addTask(title: String, description: String) {
        viewModelScope.launch {
            try {
                taskRepository.addTask(TaskModel(title = title, description = description))
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    status = LoadStatus.Error(e.message ?: "Unknown error")
                )
            }
        }
    }
}
data class HomeUiState(
    val status: LoadStatus = LoadStatus.Innit(),
    val listTask: List<TaskModel>? = null
)