package com.pschiit.smartinput.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BluetoothDisabled
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material.icons.filled.Mouse
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pschiit.smartinput.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmartInputApp(appViewModel: AppViewModel = AppViewModel()) {
    val uiState by appViewModel.uiState.collectAsState()
    Scaffold(
        modifier = Modifier.imePadding(),
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.background
                ),
                title = {
                    Text(
                        text = uiState.hostName.ifEmpty { stringResource(R.string.no_host_connected) }
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.BluetoothDisabled,
                            contentDescription = stringResource(R.string.bluetooth_disabled_icon),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { appViewModel.toggleKeyboard() }) {
                        Icon(
                            imageVector = Icons.Filled.Keyboard,
                            contentDescription = stringResource(R.string.keyboard_icon),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(innerPadding)
        ){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .clip(shape = RoundedCornerShape(24.dp))
                    .background(MaterialTheme.colorScheme.primary)
            ){
                Icon(
                    imageVector = Icons.Filled.Mouse,
                    contentDescription = stringResource(R.string.mouse_icon),
                    tint = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .size(32.dp)
                )
            }
            if(uiState.inputText.isEmpty().not()){
                Row (
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.background.copy(alpha = 0.75f))
                ){
                    Text(
                        text = uiState.inputText,
                        maxLines = 1,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = MaterialTheme.typography.displaySmall.fontSize,
                        modifier = Modifier
                            .padding(12.dp)
                    )
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview("SmartInputApp")
@Composable
private fun SmartInputAppPreview(){
    SmartInputApp()
}