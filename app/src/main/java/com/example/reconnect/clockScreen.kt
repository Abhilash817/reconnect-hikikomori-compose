package com.example.reconnect

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reconnect.roomUser.StreakViewModel

// Clock with 2 Text composable inside the Column

@Composable
fun ClockScreen(viewModel: ReconnectViewModel= viewModel(),
                onNavigateToHomeScreen: () -> Unit,
                stViewModel: StreakViewModel
) {
    val clockState by viewModel.clockStatus.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val lifeCycleOwner= LocalLifecycleOwner.current
    var playbutton by rememberSaveable {
        mutableStateOf(true)
    }
    val currentState=viewModel.timer.collectAsStateWithLifecycle()
    val minutes = (currentState.value.CurrentSeconds / 60).toString().padStart(2, '0')
    val seconds = (currentState.value.CurrentSeconds % 60).toString().padStart(2,'0')
    DisposableEffect(lifeCycleOwner) {
        val lifeCycleObserver= LifecycleEventObserver{_,event ->

            if(event== Lifecycle.Event.ON_PAUSE){
            viewModel.doAction(action= ClockScreenAction.PauseTimer)
                playbutton=true

            }

        }
        lifeCycleOwner.lifecycle.addObserver(lifeCycleObserver)


        onDispose{
            lifeCycleOwner.lifecycle.removeObserver(lifeCycleObserver)
        }
    }
    LaunchedEffect(clockState) {
        if(clockState.clockStatus==true) {
            Toast.makeText(context, "Timer Completed", Toast.LENGTH_SHORT).show()
            stViewModel.updateStreak()
        }
    }
    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->

        Box(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
                .background(color = colorResource(R.color.Warm_Beige)),
            contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier.padding(5.dp).align(Alignment.TopStart)) {
                Button(
                    modifier = Modifier.size(60.dp),
                    contentPadding = PaddingValues(0.dp),
                    onClick = {
                        onNavigateToHomeScreen()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor =Color.LightGray,
                        contentColor = colorResource(R.color.Muted)
                    ),
                    elevation = ButtonDefaults.buttonElevation(10.dp),
                    shape = CircleShape
                ) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = colorResource(R.color.Dark_Gray)
                    )
                }
            }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Card(
                        modifier = Modifier
                            .size(250.dp),
                        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.White)),
                        shape = CircleShape,
                        border = BorderStroke(10.dp, colorResource(R.color.Muted))
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "${minutes}:${seconds}",
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                color = colorResource(R.color.Dark_Gray)
                            )
                            Text(
                                text = "minutes",
                                modifier = Modifier.padding(bottom = 4.dp),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = colorResource(R.color.Medium_Gray)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                viewModel.doAction(action = ClockScreenAction.ResetTimer)
                                playbutton = true
                            },
                            modifier = Modifier.size(70.dp),
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(
                                colorResource(R.color.Medium_Gray),
                                contentColor = colorResource(R.color.Dark_Gray)
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Replay,
                                contentDescription = null,
                                modifier = Modifier.size(40.dp),
                            )
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                        Button(
                            onClick = {
                                if (playbutton) {
                                    viewModel.doAction(ClockScreenAction.StartTimer)
                                } else {
                                    viewModel.doAction(ClockScreenAction.PauseTimer)
                                }
                                playbutton = !playbutton
                            },
                            modifier = Modifier.size(70.dp),
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(
                                colorResource(R.color.Secondary_Green),
                                contentColor = colorResource(R.color.Dark_Gray)
                            )
                        ) {
                            Icon(
                                imageVector = if (!playbutton) Icons.Default.Pause else Icons.Default.PlayArrow,
                                contentDescription = null,
                                modifier = Modifier.size(40.dp),
                                tint = colorResource(R.color.Dark_Gray)
                            )
                        }

                    }
                }
            }
        }
    }

