package com.example.reconnect.roomUser

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserInfo)

    @Query("SELECT * FROM User WHERE email = :email AND password = :password")
    suspend fun getUserByEmail(email: String,password:String): UserInfo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStreak(userStreak:UserStreak)


    @Query(value="Select id,streak,longestStreak,lastStreakDate,activeDays from UserStreak ")
     fun getStreak(): Flow<UserStreak?>


     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertActivity(userUsage: UserUsageInsights)

     @Query("Select * From UserUsageInsights Where date=:date")
      fun getUsageByDate(date:String):Flow<UserUsageInsights?>

      @Insert(onConflict = OnConflictStrategy.REPLACE)
      suspend fun insertStreakHistory(userStreakHistory: UserStreakHistory)

      @Query("Select * From UserStreaKHistory Where date between :startDate and :endDate ")
      fun getStreakHistoryByDate(startDate: LocalDate, endDate:LocalDate):Flow<List<UserStreakHistory>>






}