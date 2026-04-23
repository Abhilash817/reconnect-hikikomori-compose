package com.example.reconnect


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.MobileFriendly
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PunchClock
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reconnect.roomUser.StreakViewModel
import com.example.reconnect.composables.ReconnectTopAppBar
import com.example.reconnect.usageManager.todayScreenTime
import com.example.reconnect.usageManager.yesterdayScreenTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: ReconnectViewModel= viewModel(), onNavigateToClockScreen: () -> Unit={},
               paddingValues: PaddingValues,
               stViewModel: StreakViewModel=viewModel()){
    val streakState=stViewModel.streakFlow.collectAsStateWithLifecycle()
    val streakCount = streakState.value?.streak ?: 0
    val scrollState = rememberScrollState()
    var checkedState by remember {
        mutableStateOf(false)
    }
    var checkBoxText by remember { mutableStateOf("Mark as completed") }
    val context=LocalContext.current
    val currentState=viewModel.timer.collectAsStateWithLifecycle()
    val minutes=(currentState.value.CurrentSeconds/60).toString().padStart(2,'0')
    val seconds=(currentState.value.CurrentSeconds%60).toString().padStart(2,'0')
    val todayScreenTime=todayScreenTime(context)
    val yesterdayScreenTime= yesterdayScreenTime(context)
    val todayScreenMinutes=(todayScreenTime/1000/60)%60
    val todayScreenHours=(todayScreenTime/1000/60)/60
    Scaffold(
        modifier = Modifier.fillMaxSize().padding(paddingValues),
        topBar = {ReconnectTopAppBar() },
        containerColor = colorResource(R.color.Warm_Beige),
        contentWindowInsets = BottomAppBarDefaults.windowInsets
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = 30.dp)
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(10.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = colorResource(R.color.Muted)),
                elevation = CardDefaults.cardElevation(10.dp),
            ) {
                Column(modifier = Modifier
                    .wrapContentHeight()
                    .padding(16.dp)) {
                    Text(
                        text = "\"Every moment offline is a chance to reconnect with yourself and others\"",
                        fontSize = 16.sp,
                        lineHeight = 30.sp,
                        maxLines = 3
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))


            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(10.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = colorResource(R.color.White)),
                elevation = CardDefaults.cardElevation(10.dp),

                ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(modifier = Modifier.size(60.dp),
                        shape = CircleShape,colors= CardDefaults.cardColors(containerColor = Color.Transparent)) {
                        Icon(
                            imageVector = Icons.Default.MobileFriendly,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize().align(Alignment.CenterHorizontally).padding(15.dp),
                            tint = colorResource(R.color.Primary_Blue)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, bottom = 16.dp, start = 10.dp, end = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Today's,Screen Time",
                            modifier = Modifier.padding(bottom = 4.dp),
                            fontSize = 14.sp,
                            color = colorResource(R.color.Medium_Gray)
                        )
                        Text(

                            text = "${todayScreenHours}h:${todayScreenMinutes}m",
                            modifier = Modifier.padding(bottom = 4.dp),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.Dark_Gray)
                        )

                        Text(
                            text = yesterdayVsToday(todayScreenTime,yesterdayScreenTime),
                            modifier = Modifier.padding(bottom = 4.dp),
                            fontSize = 12.sp,
                            color = colorResource(R.color.Medium_Gray)
                        )
                    }
                }

            }
            // Clock and Mediation Block
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(top = 20.dp, start = 10.dp, end = 10.dp, bottom = 10.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = colorResource(R.color.White)),
                elevation = CardDefaults.cardElevation(10.dp),
            ){
                Row(
                    modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Icon(
                        imageVector = Icons.Default.PunchClock,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = colorResource(R.color.Secondary_Green)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = "Daily Meditation",
                            modifier = Modifier.padding(bottom = 10.dp),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = colorResource(R.color.Medium_Gray)
                        )
                        Text(
                            text = "$minutes minutes left",
                            modifier = Modifier.padding(bottom = 4.dp),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.Dark_Gray)
                        )

                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Clock with 2 Text composable inside the Column
                    Card(
                        modifier = Modifier
                            .size(200.dp)
                            .padding(20.dp),
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
                                text = "minutes left",
                                modifier = Modifier.padding(bottom = 4.dp),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = colorResource(R.color.Medium_Gray)
                            )
                        }

                    }
                    Button(
                        onClick = {onNavigateToClockScreen()
                                  Log.d("HomeScreen","Button Clicked")},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.Secondary_Green),
                            contentColor = colorResource(R.color.Dark_Gray)
                        ),
                        shape = RoundedCornerShape(15.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            modifier = Modifier.size(30.dp),
//                            tint = colorResource(R.color.Dark_Gray)
                        )
                        Text(
                            text = "Start Meditation",
                            modifier = Modifier.padding(start = 4.dp),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
//                            color=colorResource(R.color.Dark_Gray)
                        )

                    }
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(top = 20.dp, start = 10.dp, end = 10.dp, bottom = 10.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = colorResource(R.color.White)),
                elevation = CardDefaults.cardElevation(10.dp),
            ) {
                Row(
                    modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Icon(
                        imageVector = Icons.Default.People,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = colorResource(R.color.Accent_Beige)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = "Social Connection",
                            modifier = Modifier.padding(bottom = 10.dp),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = colorResource(R.color.Medium_Gray)
                        )
                        Text(
                            text = "Have a meaningful conservation",
                            modifier = Modifier.padding(bottom = 4.dp),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.Dark_Gray)
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = checkedState,
                                onCheckedChange = {
                                    checkedState = !checkedState
                                    if (checkedState) {
                                        checkBoxText = "Completed"
                                        stViewModel.updateStreak()
                                    } else {
                                        checkBoxText = "Mark as completed"
                                    }

                                },
                                modifier = Modifier.padding(bottom = 4.dp),
                                colors = CheckboxDefaults.colors(
                                    checkedColor = colorResource(R.color.Secondary_Green),
                                    uncheckedColor = colorResource(R.color.Muted)
                                )
                            )

                            Text(
                                text = checkBoxText,
                                modifier = Modifier.padding(bottom = 4.dp),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (checkedState)
                                    colorResource(R.color.Secondary_Green)
                                else
                                    colorResource(R.color.Muted)
                            )

                        }
                    }
                }
            }
            // Streak Container
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(top = 20.dp, start = 10.dp, end = 10.dp, bottom = 40.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = colorResource(R.color.White)),
                elevation = CardDefaults.cardElevation(10.dp),
            ) {
                Row(
                    modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Default.LocalFireDepartment,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = colorResource(R.color.Orange)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = "Current Streak",
                            modifier = Modifier.padding(bottom = 10.dp),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = colorResource(R.color.Orange)
                        )

                        Text(
                            text = "${streakCount}days",
                            modifier = Modifier.padding(bottom = 4.dp),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.Orange)
                        )

                    }

                }
            }
           

        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(paddingValues = PaddingValues(20.dp), onNavigateToClockScreen = {})

}

fun yesterdayVsToday(todayUsage:Long,yesterdayUsage:Long):String {
    var extraUsage: Long
    if (todayUsage > yesterdayUsage) {
        extraUsage = todayUsage - yesterdayUsage
        return "+${(extraUsage / 1000 / 60) / 60}h ${(extraUsage / 1000 / 60) % 60}m"
    } else {
        extraUsage=yesterdayUsage-todayUsage
        return "-${(extraUsage/1000/60)/60}h ${(extraUsage/1000/60)%60}m"

    }
}