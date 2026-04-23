package com.example.reconnect

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.rememberNavController
import com.example.reconnect.roomUser.AuthViewModel

@Composable
fun LoginSigninApp(vm: AuthViewModel){
    val navController= rememberNavController()
    Scaffold (modifier=Modifier.fillMaxSize().background(color = colorResource(R.color.Accent_Beige)).systemBarsPadding(),
        containerColor = Color.Transparent){
        paddingValues ->
        LoginSignUpNavHost(vm,navController,paddingValues)

    }
}
