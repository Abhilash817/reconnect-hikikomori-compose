package com.example.reconnect.roomUser

import androidx.room.TypeConverter
import java.time.LocalDate


class Converters {
    @TypeConverter
    fun toString(date: LocalDate):String=date.toString()

    @TypeConverter
    fun toDate(dateString:String):LocalDate=LocalDate.parse(dateString)
}