package com.example.reconnect

import android.provider.ContactsContract
import androidx.room.Entity
import androidx.room.Index

import androidx.room.PrimaryKey


data class ReconnectClock(val CurrentSeconds: Int)


sealed interface ClockScreenAction{
    data object StartTimer: ClockScreenAction
    data object PauseTimer: ClockScreenAction
    data object ResetTimer: ClockScreenAction
}

//@Serializable
//data object HomeScreenRoute
//
//@Serializable
//data object ClockScreenRoute

@Entity
data class NoteData(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    val age:Int
)

sealed interface LoginSignUpRoute{
data object Login: LoginSignUpRoute
    data class SignUp(val route:String): LoginSignUpRoute

}


