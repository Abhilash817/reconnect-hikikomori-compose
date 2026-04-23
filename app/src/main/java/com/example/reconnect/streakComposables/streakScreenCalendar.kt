package com.example.reconnect.streakComposables

import androidx.compose.runtime.Composable
import java.time.YearMonth

@Composable
fun getCalendarRow(month: YearMonth):List<CalendarRow> {
    val monthDays = month.lengthOfMonth()
    val monthStartDay = month.atDay(1)
    val startDay = monthStartDay.dayOfWeek.value
    val dayOffset = startDay % 7
    val cells = mutableListOf<CalendarCells>()

    //For Adding Cells before the start of the months date
    repeat(dayOffset) {
        cells.add(CalendarCells(null))
    }


    //Creating the cells inside the months
    for (days in 1..monthDays) {
        cells.add(
            CalendarCells(month.atDay(days))
        )
    }

    //For adding the cells after the end of the months date
    while (cells.size % 7 != 0) {
        cells.add(CalendarCells(null))
    }
    val row=cells.chunked(7).mapIndexed{
        index,list->
        CalendarRow(index,list)
    }

    return row
}



