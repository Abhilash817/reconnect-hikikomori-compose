package com.example.reconnect

import android.graphics.drawable.Icon
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Insights
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.reconnect.RoomUser.StreakViewModel
import com.example.reconnect.composables.ReconnectStreakTopAppBar


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun StreakScreen(onBack:()->Unit={}, paddingValues: PaddingValues=PaddingValues(0.dp),
                 stViewModel: StreakViewModel=viewModel()) {
    val streakState by stViewModel.streakFlow.collectAsStateWithLifecycle()
    BackHandler {
        onBack()
    }
    Scaffold(modifier=Modifier.padding(paddingValues),
        containerColor = colorResource(R.color.Muted)
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
                .background(colorResource(R.color.Muted)),
                contentPadding = paddingValues,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                ReconnectStreakTopAppBar(stViewModel)
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .background(color = colorResource(R.color.Muted)),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    //1st card with streak days and longest streak

                    StatsCard(modifier=Modifier.weight(1f),
                        text1="Longest Streak",
                        text2="${streakState?.longestStreak}D",
                        icon = Icons.Default.CheckCircle,
                        iconColor=colorResource(R.color.Primary_Blue))

                    Spacer(modifier = Modifier.width(20.dp))

                    //2nd card in the row composable with total active days

                    StatsCard(modifier = Modifier.weight(1f),
                        text1="Active      Days",
                        text2="45 D",
                        icon = Icons.Default.Insights,
                        iconColor = colorResource(R.color.Secondary_Green)

                    )

                }

            }
            item {
                Card(modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(500.dp)
                    .padding(top = 20.dp),
                    colors = CardDefaults.cardColors(containerColor = colorResource(R.color.White)),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Column(modifier=Modifier.fillMaxWidth()) {

                        //Activity section
                        ActivitySection()

                        Row(modifier = Modifier.fillMaxWidth().height(300.dp)) {
                            Column(
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Spacer(modifier = Modifier.height(33.dp))

                                repeat(7) { week ->
                                    Text(
                                        "W${7 - week}",
                                        modifier = Modifier.size(40.dp).padding(4.dp),
                                        style = TextStyle(color = colorResource(R.color.Medium_Gray))
                                    )
                                }

                            }

                            Column() {
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    listOf("S", "M", "T", "W", "T", "F", "S").forEach {
                                        Text(
                                            text = it,
                                            modifier = Modifier.weight(1f).padding(start = 20.dp),
                                            style = TextStyle(
                                                fontSize = 15.sp,
                                                color = colorResource(R.color.Medium_Gray)
                                            )
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                LazyVerticalGrid(
                                    modifier = Modifier.size(300.dp),
                                    columns = GridCells.Fixed(7),
                                    userScrollEnabled = false
                                ) {

                                    items(49) {
                                        Card(
                                            modifier = Modifier.weight(1f).padding(4.dp)
                                                .size(30.dp),
                                            colors = CardDefaults.cardColors(
                                                containerColor = Color.LightGray
                                            ),
                                            shape = CircleShape
                                        ) {}
                                    }
                                }

                            }
                        }
                        Spacer(modifier=Modifier.height(20.dp))
                        HorizontalDivider(modifier = Modifier.fillMaxWidth(0.8f).align(Alignment.CenterHorizontally),
                            thickness=2.dp,color=colorResource(R.color.Muted))
                        Spacer(modifier=Modifier.height(20.dp))

                        Row(modifier=Modifier.fillMaxWidth(0.8f).height(100.dp)
                        ,horizontalArrangement = Arrangement.Center){
                            Card(modifier=Modifier.size(25.dp).padding(2.dp),
                                colors=CardDefaults.cardColors(containerColor = Color.LightGray),
                                shape=RoundedCornerShape(10.dp),
                                elevation = CardDefaults.cardElevation(10.dp),content={})
                           Box(modifier=Modifier.padding(2.dp)){ Text(text="Missed",modifier=Modifier,
                                color=colorResource(R.color.Medium_Gray))}
                            Card(modifier=Modifier.size(25.dp).padding(2.dp),
                                colors=CardDefaults.cardColors(containerColor = colorResource(R.color.Secondary_Green)),
                                shape=RoundedCornerShape(10.dp),
                                elevation = CardDefaults.cardElevation(10.dp), content = {})
                           Box(modifier=Modifier.padding(2.dp))
                           { Text(text="Completed", color = colorResource(R.color.Medium_Gray))}

                            Card(modifier=Modifier.size(25.dp).padding(2.dp),
                                colors=CardDefaults.cardColors(containerColor = colorResource(R.color.Orange)),
                                shape=RoundedCornerShape(10.dp),
                                elevation = CardDefaults.cardElevation(10.dp), content = {})

                           Box(modifier=Modifier.padding(2.dp))
                           { Text(text="Today", color = colorResource(R.color.Medium_Gray))}

                        }
                    }
                }
            }

            item{
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(0.8f).
                    height(110.dp),
                    colors = CardDefaults.cardColors(containerColor = colorResource(R.color.Gradient_Green)),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(10.dp),
                    border = BorderStroke(2.dp, Color.LightGray)
                ) {
                    Text("you're doing great keep this momentum going.Consistency is the key to building lasting habits",
                        fontSize = 18.sp, maxLines = 3, letterSpacing = 1.sp, textAlign = TextAlign.Center,color=colorResource(R.color.Dark_Gray),
                        modifier=Modifier.padding(top=10.dp,bottom=10.dp))

                }
            }

            item{
                Spacer(modifier = Modifier.height(20.dp))
            }

            item{
                Card(modifier=Modifier.fillMaxWidth(0.8f).height(270.dp),colors=CardDefaults.cardColors(containerColor = colorResource(R.color.White)),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(10.dp)){
                    Column(modifier=Modifier.fillMaxSize(),verticalArrangement=Arrangement.spacedBy(8.dp),
                        horizontalAlignment=Alignment.CenterHorizontally){
                       Box(modifier=Modifier.fillMaxWidth().padding(13.dp)){
                           Text(text="Next MileStones",modifier=Modifier.align(Alignment.TopStart),
                               style=TextStyle(fontWeight=FontWeight.Bold,fontSize=20.sp))
                       }
                        ProgressIndicator(7,10,colorResource(R.color.Primary_Blue),"Unlock Calming Sounds")
                        ProgressIndicator(7,14,colorResource(R.color.Secondary_Green),"2-week warrior badge")
                    ProgressIndicator(7,30,colorResource(R.color.Orange),"Monthly Achievment")

                    }

                }

            }
        }
    }

}

@Composable
fun StatsCard(
    modifier: Modifier,
    text1: String,
    text2:String,
    icon: ImageVector,
    iconColor: Color
){
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.White)),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .height(150.dp)
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                modifier = Modifier.size(45.dp).aspectRatio(1f).padding(end=5.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(containerColor = colorResource(R.color.Light_Orange)),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(45.dp)
                        .padding(5.dp),
                    tint = iconColor
                )
            }
            Spacer(modifier=Modifier.width(10.dp))
            Column(modifier=Modifier.weight(1f)
            ) {
                Text(
                    text = text1, style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(R.color.Medium_Gray)
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = text2, style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.Dark_Gray)
                    )
                )

            }
        }


    }
}


@Composable
fun ActivitySection(){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Card(
            modifier = Modifier.size(45.dp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = colorResource(R.color.Light_Orange)),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.CalendarMonth,
                contentDescription = null,
                modifier = Modifier
                    .size(45.dp)
                    .padding(5.dp),
                tint = colorResource(R.color.Primary_Blue)
            )

        }
        Spacer(modifier = Modifier.width(15.dp))
        Column(
        ) {
            Text(
                text = "Activity", style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = colorResource(R.color.Dark_Gray)
                )
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Last 7 weeks", style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = colorResource(R.color.Medium_Gray)
                )
            )

        }
    }
}

@Composable
fun ProgressIndicator(
    streak:Int,
    targetDay:Int,
    targetColor: Color,
    achievement:String
){
    Column(modifier=Modifier.fillMaxWidth()) {
        Row(modifier=Modifier.fillMaxWidth().padding(5.dp), horizontalArrangement = Arrangement.SpaceBetween){
            Text("${targetDay-streak}days",modifier=Modifier.padding(5.dp),
                style=TextStyle(fontSize=16.sp,color=colorResource(R.color.Dark_Gray), fontWeight = FontWeight.Bold))
            Text(text=achievement,modifier=Modifier.padding(5.dp),
                style = TextStyle(fontSize=16.sp,color=colorResource(R.color.Medium_Gray), fontWeight = FontWeight.Normal))

        }
        LinearProgressIndicator(
            progress = { streak / targetDay.toFloat() },
            modifier = Modifier.fillMaxWidth().padding(5.dp).height(10.dp),
            color = targetColor,
            trackColor = Color.LightGray,
        )
    }


}