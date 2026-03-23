package com.example.reconnect.RoomUser

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "User", indices =[Index(value = ["email"], unique = true)])
data class UserInfo(
    @PrimaryKey
    val id:String= UUID.randomUUID().toString(),
    val name:String,
    val age:Int,
    val email: String,
    val mobileNo:String,
    val password:String
)
