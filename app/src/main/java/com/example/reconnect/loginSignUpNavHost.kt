package com.example.reconnect

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.reconnect.roomUser.AuthViewModel

@Composable
fun LoginSignUpNavHost(vm: AuthViewModel, navController: NavHostController, paddingValues: PaddingValues){
    NavHost(navController, startDestination = "LoginRoute"){
        composable("LoginRoute"){
            LoginScreen(vm,paddingValues,
                onNavigateToSignUp={
                    navController.navigate("SignUpRoute")
                })
        }
    composable("SignUpRoute"){
        SignUpScreen(vm,paddingValues,
            onNavigateToLogin={
                navController.navigate("LoginRoute")
            })

    }
    }

}