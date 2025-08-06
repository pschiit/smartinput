package com.pschiit.smartinput

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.pschiit.smartinput.ui.AppViewModel
import com.pschiit.smartinput.ui.SmartInputApp
import com.pschiit.smartinput.ui.theme.SmartInputTheme

class MainActivity : ComponentActivity() {
    private lateinit var _appViewModel : AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _appViewModel = AppViewModel(
            inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        )

        enableEdgeToEdge()
        setContent {
            SmartInputTheme {
                SmartInputApp(
                    appViewModel = _appViewModel
                )
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        val newInputText = _appViewModel.uiState.value.inputText + event.unicodeChar.toChar()
        _appViewModel.updateInputText(newInputText)
        return super.onKeyDown(keyCode, event)
    }
}