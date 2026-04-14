package com.example.reconnect

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@Composable
fun LoadingScreen(){
    Scaffold(modifier=Modifier.fillMaxSize().
    background(color = colorResource(R.color.Accent_Beige)).systemBarsPadding(),){
        paddingValues ->
        Box(modifier=Modifier.fillMaxSize().padding(paddingValues).
        background(colorResource(R.color.Warm_Beige)),
            contentAlignment = Alignment.Center){
            CircularProgressIndicator(color=colorResource(R.color.Light_Orange),
                strokeWidth=5.dp, modifier = Modifier.size(50.dp))
        }

    }
}