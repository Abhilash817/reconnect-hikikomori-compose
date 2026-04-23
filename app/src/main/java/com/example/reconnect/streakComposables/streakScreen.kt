package com.example.reconnect.streakComposables

import androidx.activity.compose.BackHandler

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Insights
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reconnect.R

import com.example.reconnect.roomUser.StreakViewModel
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
                        .height(100.dp)
                        .background(color = colorResource(R.color.Muted)),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    //1st card with streak days and longest streak

                    StatsCard(modifier=Modifier.weight(1f),
                        text1="Longest Streak",
                        text2="${streakState.longestStreak}D",
                        icon = Icons.Default.CheckCircle,
                        iconColor=colorResource(R.color.Primary_Blue))

                    Spacer(modifier = Modifier.width(20.dp))

                    //2nd card in the row composable with total active days

                    StatsCard(modifier = Modifier.weight(1f),
                        text1="Active  Days",
                        text2="${streakState.activeDays} D",
                        icon = Icons.Default.Insights,
                        iconColor = colorResource(R.color.Secondary_Green)

                    )

                }

            }
            item{
                CalendarSectionCard()
            }


            item{
                Spacer(modifier = Modifier.height(20.dp))
            }



            item{
                StreakProgressSection(stViewModel)
            }
            item{
                Spacer(modifier=Modifier.height(20.dp))
            }
        }
    }

}
