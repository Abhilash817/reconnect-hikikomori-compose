package com.example.reconnect.RoomUser

import android.app.Application
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

class StreakViewModel(application: Application): AndroidViewModel(application) {

        val localDb = DatabaseProvider.getDatabase(application)

        val repository = AuthRepository(localDb.userDao())

    val streakFlow: StateFlow<UserStreak?> =repository.getStreak().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    fun updateStreak(){
        viewModelScope.launch{
            val streak=streakFlow.value
            val today=getToday()
            val yesterday=today-1
            if(streak!=null){
                if(streak.lastStreakDate==today){
                    repository.insertStreak(streak)
                }
                else if(streak.lastStreakDate==yesterday){
                    repository.insertStreak(
                       streak.copy(
                            streak=streak.streak+1,
                            longestStreak = streak.longestStreak+1,
                            lastStreakDate = today
                       )
                    )
                }
                else{
                    repository.insertStreak(streak.copy(
                        streak=streak.streak+1,
                        longestStreak = streak.longestStreak+1,
                        lastStreakDate = today
                    ))
                }
            }
            else{
                repository.insertStreak(
                    UserStreak(
                        id=1,
                        streak = 1,
                        longestStreak = 1,
                        lastStreakDate = today
                ))

            }
        }
    }


    fun getToday():Long{
        return System.currentTimeMillis()/(24*60*60*1000)
    }

    fun getNewToday():String{
        val date= LocalDate.now()
        return "${date.dayOfMonth}-${date.month}-${date.year}"
    }


}