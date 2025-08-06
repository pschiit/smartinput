package com.pschiit.smartinput.ui

data class AppState(
    val inputText: String = "",
    val hostName: String = "",
    val showDevicesDialog: Boolean = false,
)