package com.example.reconnect.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Fireplace
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

import com.example.reconnect.R


@Composable
fun ReconnectBottomAppBar(navController: NavHostController ) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination=navBackStackEntry?.destination?.route
    NavigationBar(modifier=Modifier.fillMaxWidth(),
        containerColor = colorResource(R.color.White)
    , tonalElevation = 0.dp) {
        NavigationBarItem(
            selected =(currentDestination=="HomeRoute"),
            onClick = { navController.navigate("HomeRoute"){
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }

                      },
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null,
               )},
            label = {Text(text = "Home")},
            colors = NavigationBarItemDefaults.colors(indicatorColor =Color.Transparent,
                selectedIconColor = colorResource(R.color.Primary_Blue),)

        )

        NavigationBarItem(
            selected = (currentDestination=="StreakRoute"),
            onClick = { navController.navigate("StreakRoute")
            {
                popUpTo(navController.graph.findStartDestination().id){
                    saveState=true
                }
                launchSingleTop=true
                restoreState=true
            }},
            icon = { Icon(imageVector = Icons.Default.Fireplace, contentDescription = null)},
            label = {Text(text = "Streak")},
            colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent,
                disabledIconColor = colorResource(R.color.Muted), selectedIconColor = colorResource(R.color.Terracotta)))



        NavigationBarItem(
            selected = (currentDestination=="SettingsRoute"),
            onClick = {navController.navigate("SettingsRoute"){
                popUpTo(navController.graph.findStartDestination().id){
                    saveState=true
                }
                launchSingleTop=true
                restoreState=true
            } },
            icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = null)},
            label = {Text(text = "Settings")},
            colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent,
                disabledIconColor = colorResource(R.color.Muted), selectedIconColor = colorResource(R.color.Secondary_Green))
            )

    }
}