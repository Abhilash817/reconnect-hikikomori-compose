package com.example.reconnect

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DatePicker
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.YearMonth
import java.util.Date

//@Preview(showSystemUi=true)
//@Composable
//fun Practice(){
//    val month= YearMonth.now()
//    val monthDays=month.lengthOfMonth()
//    val monthStartDay=month.atDay(1)
//    val startDay=monthStartDay.dayOfWeek.value
//    val offset=startDay%7
//    val cells = mutableListOf<CalendarCell>()
//
//    val streakMap=mapOf()
//
//    for(days in 1..monthDays) {
//        cells.add(
//            CalendarCell(month.atDay(days))
//        )
//    }
//
//    val rows=cells.chunked(7).mapIndexed{
//        index,list->
//        CalendarRow(index,list)
//    }
//
//
//    Box(
//        modifier=Modifier.fillMaxSize(),
//        contentAlignment= Alignment.Center
//    ) {
//
//        LazyColumn(modifier=Modifier.size(500.dp)) {
//            items(cells){
//                Text(text=it.localDate.toString())
//            }
//
//            items(rows[0]){
//
//                    Row(modifier = Modifier.height(50.dp)) {
//                        Text(it.localDate.toString())
//
//                         }
//                    }
//
//            }
//        }
//
//
//    }
//
//
//
//
//data class CalendarCell(
//    val localDate: LocalDate?
//)
//
//data class CalendarRow(
//    val weekNo:Int,
//    val days:List<CalendarCell>
//)