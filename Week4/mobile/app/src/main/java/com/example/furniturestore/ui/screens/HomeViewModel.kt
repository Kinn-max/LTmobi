package com.example.furniturestore.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.furniturestore.common.enum.LoadStatus
import com.example.furniturestore.model.MainResponse
import com.example.furniturestore.repositories.MainLog
import com.example.furniturestore.repositories.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


data class HomeUiState(
    val status: LoadStatus = LoadStatus.Innit(),
    val mainResponse: MainResponse? = null
)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val log: MainLog?,
    val taskRepository: TaskRepository?=null,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()
    constructor() : this(null) {
    }
    init {
        loadHomeApp()
    }

     fun loadHomeApp(){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(status = LoadStatus.Loading())
            try {
                val data = taskRepository?.loadHome()
                _uiState.value = _uiState.value.copy(
                    mainResponse = data,
                    status = LoadStatus.Success()
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(status = LoadStatus.Error(e.message ?: "Unknown error"))
            }
        }
    }

}