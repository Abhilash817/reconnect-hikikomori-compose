package com.example.reconnect

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

@Composable
fun ReconnectNavHost(navController: NavHostController,paddingValues: PaddingValues){
    val viewModel:ReconnectViewModel= viewModel()

    NavHost(navController, startDestination = "BottomBarRoute"){
        val onNavigateToHomeScreen= {
            if (!navController.popBackStack()) {
                navController.navigate("HomeRoute")
            }
        }
        val onNavigateToClockScreen= {
            navController.navigate("SideBarRoute")
        }


        navigation(
            startDestination = "HomeRoute",
            route = "BottomBarRoute"
        ){
            composable("HomeRoute") {
                HomeScreen(viewModel,onNavigateToClockScreen,paddingValues)
            }
            composable("StreakRoute") {
                StreakScreen(  {navController.navigate("HomeRoute"){
                    popUpTo(navController.graph.findStartDestination().id){
                        inclusive=true
                    }
                }},paddingValues = paddingValues)

            }
            composable("SettingsRoute") {
                SettingsScreen({
                    navController.navigate("HomeRoute"){
                        popUpTo(navController.graph.findStartDestination().id){
                            inclusive=true
                        }
                    }},paddingValues = paddingValues)
            }
        }
        navigation(
            startDestination = "ClockScreenRoute",
            route="SideBarRoute"
        ){
          composable("ClockScreenRoute"){
                ClockScreen(viewModel = viewModel,onNavigateToHomeScreen)
            }
        }
    }
}