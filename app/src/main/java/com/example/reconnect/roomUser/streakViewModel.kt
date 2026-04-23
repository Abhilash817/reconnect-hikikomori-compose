package com.example.reconnect.roomUser

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.YearMonth
import java.time.ZoneId

@OptIn(ExperimentalCoroutinesApi::class)
class StreakViewModel(application: Application): AndroidViewModel(application) {

        val localDb = DatabaseProvider.getDatabase(application)

        val repository = AuthRepository(localDb.userDao())

    val streakFlow: StateFlow<UserStreak> =repository.getStreak()
        .map{streak->
            streak?:UserStreak(1,0,0)
        }
        .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UserStreak(1,0,0)
    )

    val yearMonth = MutableStateFlow(YearMonth.now())

    val streakHistoryFlow: StateFlow<Map<LocalDate, StreakStatus>> =yearMonth.flatMapLatest {
        repository.getStreakHistoryByDate(it.atDay(1),it.atEndOfMonth())
    }.map{list->
        list.associate{
            it.date to it.streakStatus
        }

    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyMap()
    )

    fun updateStreak() {
        viewModelScope.launch {
            val streak = streakFlow.value
            val today = getToday()
            val yesterday = today - 1
            val todayDate = LocalDate.now()
            var lastStreakLocalDate = longTimeToLocalDate(streak.lastStreakDate)
            var filledDate = lastStreakLocalDate.plusDays(1)


            if (streak.streak != 0) {

                when (streak.lastStreakDate) {

                    today -> {
                        insertUserStreakHistory(todayDate, StreakStatus.Completed)
                    }

                    yesterday -> {
                        repository.insertStreak(
                            streak.copy(
                                streak = streak.streak + 1,
                                longestStreak = streak.longestStreak + 1,
                                lastStreakDate = today,
                                activeDays = streak.activeDays + 1
                            )
                        )
                        insertUserStreakHistory(todayDate, StreakStatus.Completed)
                    }

                    else ->{
                        repository.insertStreak(
                            streak.copy(
                                streak = 1,
                                longestStreak = streak.longestStreak + 1,
                                lastStreakDate = today,
                                activeDays = streak.activeDays + 1
                            )
                        )


                        while (filledDate.isBefore(todayDate)) {
                            lastStreakLocalDate = lastStreakLocalDate.plusDays(1)
                            insertUserStreakHistory(
                                lastStreakLocalDate, StreakStatus.NotCompleted
                            )
                            filledDate = filledDate.plusDays(1)
                        }
                        insertUserStreakHistory(todayDate, StreakStatus.Completed)

                    }
                }
            }

                else{
                    repository.insertStreak(
                        UserStreak(
                            id = 1,
                            streak = 1,
                            longestStreak = 1,
                            lastStreakDate = today,
                            activeDays = streak.activeDays + 1

                        )
                    )
                    insertUserStreakHistory(todayDate, StreakStatus.Completed)


                }

        }
    }


    fun getToday():Long{
        return System.currentTimeMillis()/(24*60*60*1000)
    }



   suspend fun insertUserStreakHistory(date: LocalDate, streakState: StreakStatus) {
            repository.insertStreakHistory(
                UserStreakHistory(date, streakState)
            )
        }


    fun longTimeToLocalDate(time:Long): LocalDate{
        val millisInDay=86400000L
        val date= Instant.ofEpochMilli(millisInDay*time)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
        Log.d("StreakError","$date  ${millisInDay*time}  $time  ${streakFlow.value.lastStreakDate}")

        return date
    }

    fun insertDummyData() {
        val ottotoi:Long = getToday() - 5
        viewModelScope.launch {
            repository.insertStreak(
                userStreak = UserStreak(
                    id = 1,
                    streak = 1,
                    longestStreak = 1,
                    lastStreakDate = ottotoi
                )
            )
        }
    }
}