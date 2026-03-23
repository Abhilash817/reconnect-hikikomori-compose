package com.example.reconnect

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

@Composable
fun SettingsScreen(onBack:()->Unit, paddingValues: PaddingValues){
    BackHandler{
      onBack()
    }
    Column(modifier = Modifier.fillMaxSize()
        .background(colorResource(R.color.Muted))
        .padding(paddingValues)
        , horizontalAlignment = Alignment.CenterHorizontally) {
        Card {
            Text("Settings Screen")
        }
    }
}