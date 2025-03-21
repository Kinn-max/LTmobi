package com.example.nodemanager.ui.screens.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nodemanager.common.enum.LoadStatus
import com.example.nodemanager.models.NoteItem
import com.example.nodemanager.repository.Api
import com.example.nodemanager.repository.MainLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val notes:List<NoteItem> = emptyList(),
    val status:LoadStatus = LoadStatus.Innit(),
    val selectedIndex:Int = -1
)

@HiltViewModel
class HomeViewModel @Inject constructor(private val log:MainLog?,private val api:Api?):ViewModel() {
    val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun  reset(){
        _uiState.value = _uiState.value.copy(status = LoadStatus.Innit())
    }
    fun loadNotes(){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(status =  LoadStatus.Loading())
            try {
                if(api != null){
                    val loadNotes =api.loadNotes()
                    _uiState.value = _uiState.value.copy(notes = loadNotes, status = LoadStatus.Success())
                }
            }catch (ex: Exception){
                _uiState.value = _uiState.value.copy(status =  LoadStatus.Error(ex.toString()))
            }
        }
    }
    fun deleteNote(dt:Long){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(status =  LoadStatus.Loading())
            try {
                if(api != null){
                    api.deleteNote(dt)
                    val loadNotes =api.loadNotes()
                    _uiState.value = _uiState.value.copy(notes = loadNotes , status = LoadStatus.Success())
                }
            }catch (ex: Exception){
                _uiState.value = _uiState.value.copy(status =  LoadStatus.Error(ex.toString()))
            }
        }
    }
    fun selectNote(index: Int){
        _uiState.value = _uiState.value.copy(selectedIndex = index)
    }
}
