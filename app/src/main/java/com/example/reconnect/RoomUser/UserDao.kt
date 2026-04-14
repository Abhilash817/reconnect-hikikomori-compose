package com.example.reconnect.RoomUser

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserInfo)

    @Query("SELECT * FROM User WHERE email = :email AND password = :password")
    suspend fun getUserByEmail(email: String,password:String): UserInfo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStreak(userStreak:UserStreak)


    @Query(value="Select id,streak,longestStreak,lastStreakDate from UserStreak ")
     fun getStreak(): Flow<UserStreak?>


     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertActivity(userUsage: UserUsageInsights)

     @Query("Select * From UserUsageInsights Where date=:date")
      fun getUsageByDate(date:String):Flow<UserUsageInsights?>



}