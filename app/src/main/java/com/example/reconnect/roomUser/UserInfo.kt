package com.example.reconnect.roomUser

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.Date
import java.util.UUID

@Entity(tableName = "User", indices =[Index(value = ["email"], unique = true)])
data class UserInfo(
    @PrimaryKey
    val id:String= "",
    val name:String,
    val age:Int,
    val email: String,
    val mobileNo:String,
    val password:String
)


@Entity(tableName = "UserStreak")
data class UserStreak(
    @PrimaryKey
    val id:Int=1 ,
    val streak:Int=0,
    val longestStreak:Int=0,
    val lastStreakDate:Long=0,
    val activeDays:Int=0
)

@Entity(tableName="UserStreakHistory")
data class UserStreakHistory(
    @PrimaryKey
    val date: LocalDate,
    val streakStatus: StreakStatus=StreakStatus.NotCompleted
)







enum class StreakStatus {
    Completed ,
    NotCompleted
}





@Entity(tableName="UserActivity")
data class UserActivity(
    @PrimaryKey
    val id:Int=1,
    val meditatedSeconds:Long=0,
    val socialStatus:Boolean=false,
    val date: Date= Date()
)

@Entity(tableName="UserAchievements")
data class UserAchievements(
    @PrimaryKey
    val id:Int=1,
    val Achievements:List<String> = emptyList(),
    val AchievementsCompleted:List<String> = emptyList()
)

@Entity(tableName="UserUsageInsights")
data class UserUsageInsights(
    @PrimaryKey
    val date:String="",
    val screenTime:Long=0L,
    val unlockCounts:Int=0,
    val behaviorScore:Int=0
)




data class AuthState(
val isLoading:Boolean=false,
    val isLoggedIn:Boolean=false,
    val error:String?=null
)

sealed class UserState{
    object LoggedIn:UserState()
    object LoggedOut:UserState()
    object isLoading:UserState()
}


