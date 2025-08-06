package com.pschiit.smartinput.ui

import android.os.CountDownTimer
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel(
    inputMethodManager: InputMethodManager? = null,
) : ViewModel() {
    private val _uiState = MutableStateFlow(AppState())
    private val _inputMethodManager = inputMethodManager
    private val _inputTextTimer = object : CountDownTimer(3000,1000){
        override fun onTick(millisUntilFinished: Long) {
        }

        override fun onFinish() {
            _uiState.update {  currentState ->
                currentState.copy(inputText ="")
            }
        }
    }

    val uiState: StateFlow<AppState> = _uiState.asStateFlow()

    fun updateInputText(inputText: String){
        _uiState.update { currentState ->
            currentState.copy(inputText = inputText)
        }
        _inputTextTimer.cancel()
        _inputTextTimer.start()
    }

    fun updateHostName(hostName: String){
        _uiState.update { currentState ->
            currentState.copy(hostName = hostName)
        }
    }

    fun toggleKeyboard(){
        _inputMethodManager?.toggleSoftInput(0,0)
    }
}