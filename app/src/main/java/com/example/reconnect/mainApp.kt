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
import com.example.reconnect.RoomUser.UserState

@Composable
fun MainApp(vm: AuthViewModel=viewModel()){
//    val isLoggedIn by vm.isLoggedIn.collectAsStateWithLifecycle()
    val userLoggedIn by vm.getUserState.collectAsStateWithLifecycle()
 when(userLoggedIn){
     UserState.LoggedIn -> {
         ReconnectApp(vm)
     }
     UserState.LoggedOut -> {
         LoginSigninApp(vm)
     }
     UserState.isLoading->{
         LoadingScreen()
     }
 }
}
