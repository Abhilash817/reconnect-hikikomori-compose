package com.example.reconnect

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding

import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults.windowInsets
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.reconnect.composables.ReconnectBottomAppBar

@Composable
fun ReconnectApp(){
    val navController= rememberNavController()
val showBottomBarState= navController.currentBackStackEntryAsState().value?.destination?.route in listOf("HomeRoute","StreakRoute","SettingsRoute")
    Scaffold(modifier = Modifier.fillMaxSize().background(color = colorResource(R.color.Accent_Beige)).systemBarsPadding(), containerColor = Color.Transparent
    , bottomBar = {
            AnimatedVisibility(showBottomBarState,
                    enter = fadeIn() + expandVertically(),
                exit =fadeOut() + shrinkVertically()
            ) {
                ReconnectBottomAppBar(navController)
            }
        })
    { paddingValues ->
        ReconnectNavHost(navController,paddingValues)
    }
}
