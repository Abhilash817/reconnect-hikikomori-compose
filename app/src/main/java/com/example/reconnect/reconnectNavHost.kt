package com.example.reconnect

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.reconnect.roomUser.AuthViewModel
import com.example.reconnect.roomUser.StreakViewModel
import com.example.reconnect.settingcomposables.SettingsScreen
import com.example.reconnect.streakComposables.StreakScreen

@Composable
fun ReconnectNavHost(navController: NavHostController,paddingValues: PaddingValues,authViewModel: AuthViewModel){
    val viewModel:ReconnectViewModel= viewModel()
    val stViewModel: StreakViewModel = viewModel()
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
                HomeScreen(viewModel,onNavigateToClockScreen,paddingValues,stViewModel)
            }
            composable("StreakRoute") {
                StreakScreen(
                    {
                        navController.navigate("HomeRoute") {
                            popUpTo(navController.graph.findStartDestination().id) {
                                inclusive = true
                            }
                        }
                    }, paddingValues = paddingValues,
                    stViewModel
                )

            }
            composable("SettingsRoute") {
                SettingsScreen({
                    navController.navigate("HomeRoute") {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                    }
                }, paddingValues = paddingValues,profileScreen= {
                    authViewModel.logOut()
                }
                )
            }
        }
        navigation(
            startDestination = "ClockScreenRoute",
            route="SideBarRoute"
        ){
          composable("ClockScreenRoute"){
                ClockScreen(viewModel = viewModel,onNavigateToHomeScreen,stViewModel)
            }
        }
    }
}
