package com.example.reconnect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reconnect.roomUser.AuthViewModel
import com.example.reconnect.roomUser.UserState

@Composable
fun MainApp(vm: AuthViewModel=viewModel()){
//    val isLoggedIn by vm.isLoggedIn.collectAsStateWithLifecycle()
    val userLoggedIn by vm.isLoggedIn.collectAsStateWithLifecycle()
// when(userLoggedIn){
//     UserState.LoggedIn -> {
//         ReconnectApp(vm)
//     }
//     UserState.LoggedOut -> {
//         LoginSigninApp(vm)
//     }
//     UserState.isLoading->{
//         LoadingScreen()
//     }
// }
    if(userLoggedIn.isLoggedIn){
        ReconnectApp(vm)
    }
    else{
        LoginSigninApp(vm)
    }
}
