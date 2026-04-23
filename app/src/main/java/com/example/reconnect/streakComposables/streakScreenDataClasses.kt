package com.example.reconnect.streakComposables

import com.example.reconnect.roomUser.StreakStatus
import java.time.LocalDate

data class CalendarCells(
    val date: LocalDate?
)

data class CalendarRow(
    val weekNo:Int,
    val days:List<CalendarCells>
)


