package com.example.nodemanager.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nodemanager.common.enum.LoadStatus
import com.example.nodemanager.repository.Api
import com.example.nodemanager.repository.MainLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val statusLoad:LoadStatus = LoadStatus.Innit(),
)

@HiltViewModel
class LoginViewModel @Inject constructor
    (private val log: MainLog?,
     private val api: Api?):ViewModel()
{
    val _uiState = MutableStateFlow(LoginUiState())
    val  uiState = _uiState.asStateFlow()

    fun updateUserName(username: String) {
        _uiState.value = _uiState.value.copy(username = username)
    }

    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun reset(){
        _uiState.value = _uiState.value.copy(statusLoad = LoadStatus.Innit())
    }
    fun login(){
        viewModelScope.launch{
            _uiState.value = _uiState.value.copy(statusLoad = LoadStatus.Loading())
            try {
                var login = api?.login(uiState.value.username,uiState.value.password)
                _uiState.value = _uiState.value.copy(statusLoad = LoadStatus.Success())
            }catch (ex:Exception){
                _uiState.value = _uiState.value.copy(statusLoad = LoadStatus.Error(ex.message.toString()))
            }
        }
    }
}