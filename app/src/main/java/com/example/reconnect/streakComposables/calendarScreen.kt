package com.example.reconnect.streakComposables

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reconnect.R
import com.example.reconnect.roomUser.StreakStatus
import com.example.reconnect.roomUser.StreakViewModel
import java.time.LocalDate
import java.time.YearMonth


@Composable
fun CalendarScreen(streakVM: StreakViewModel =viewModel()){
    val month= YearMonth.now()
    val calendarRow=getCalendarRow(month)
    val daysList=listOf("S","M","T","W","T","F","S")
    val streakMap by  streakVM.streakHistoryFlow.collectAsStateWithLifecycle()



    LazyColumn(modifier=Modifier.fillMaxWidth().height(150.dp)){
        item{
            Row(modifier=Modifier.fillMaxWidth().padding(end=20.dp,start=20.dp),
                horizontalArrangement = Arrangement.SpaceBetween){
                Spacer(modifier=Modifier.width(32.dp))
                daysList.forEach{
                    Text(text=it,
                        modifier=Modifier.weight(1f,fill=false),
                        style= TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif,
                            color=Color.LightGray
                        )
                    )
                }
            }

        }

        items(calendarRow){
            Spacer(modifier=Modifier.height(10.dp))
            CalendarRowComposable(
                row=it,
                weekMap=streakMap
            )
        }
    }


}

@Composable
fun CalendarRowComposable(
    row: CalendarRow,
    weekMap:Map<LocalDate, StreakStatus>
){
    Log.d("Streak","$weekMap")
    val currentDate=LocalDate.now()
    Row(modifier=Modifier.fillMaxWidth().padding(end=20.dp,start=20.dp),
        horizontalArrangement = Arrangement.SpaceBetween){

        //Week Number
        Text(
            text=" W${row.weekNo + 1}",
            modifier=Modifier.weight(1f),
            style=TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                color=Color.LightGray
            )
        )

        row.days.forEach {
            if(it.date==null){
               Card(modifier=Modifier.size(15.dp).weight(1f,fill = false)
               ,colors= CardDefaults.cardColors(containerColor = Color.Black),
                   shape=CircleShape){
               }
            }
            else{
                val streakStatus= weekMap[it.date]
                when(streakStatus){
                     StreakStatus.Completed->{
                         if(it.date==currentDate) {
                             Card(
                                 modifier = Modifier.size(15.dp).weight(1f, false),
                                 colors = CardDefaults.cardColors(
                                     containerColor =
                                         colorResource(R.color.Orange)
                                 ),
                                 shape = CircleShape
                             ) {}
                         }
                         else{
                             Card(
                                 modifier = Modifier.size(15.dp).weight(1f, false),
                                 colors = CardDefaults.cardColors(
                                     containerColor =
                                         colorResource(R.color.Secondary_Green)
                                 ),
                                 shape = CircleShape
                             ) {}

                         }
                    }

                     StreakStatus.NotCompleted->{
                         Card(modifier=Modifier.size(15.dp).weight(1f,false)
                             ,colors= CardDefaults.cardColors(containerColor =
                                 Color.Red),
                             shape=CircleShape){}
                     }

                    else->{
                        Card(modifier=Modifier.size(15.dp).weight(1f,false)
                            ,colors= CardDefaults.cardColors(containerColor = Color.Black),
                            shape=CircleShape){
                        }
                    }

                }


            }
        }


    }


}