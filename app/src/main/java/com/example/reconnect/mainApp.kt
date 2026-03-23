package com.example.reconnect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reconnect.RoomUser.AuthViewModel

@Composable
fun MainApp(vm: AuthViewModel=viewModel()){
    val isLoggedIn by vm.isLoggedIn.collectAsStateWithLifecycle()
    if(!isLoggedIn){
        LoginSigninApp(vm)
    }
    else{
        ReconnectApp()
    }
}