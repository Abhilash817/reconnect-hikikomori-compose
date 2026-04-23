package com.example.reconnect.roomUser

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate


class AuthRepository(private val userDao: UserDao){

     suspend fun insertUser(user:UserInfo){
        userDao.insertUser(user)
    }
    suspend fun getUserByEmail(email:String,password:String):Boolean{
        return userDao.getUserByEmail(email,password)!=null
    }

    suspend fun insertStreak(userStreak: UserStreak){
        userDao.insertStreak(userStreak)

    }

    fun getStreak(): Flow<UserStreak?> {
        return userDao.getStreak()
    }

    suspend fun insertActivity(userUsage: UserUsageInsights){
        userDao.insertActivity(userUsage)
    }

    fun getUsageByDate(date:String):Flow<UserUsageInsights?>{
        return userDao.getUsageByDate(date)
    }


    suspend fun insertStreakHistory(userStreakHistory: UserStreakHistory) {
        userDao.insertStreakHistory(userStreakHistory)
    }

    fun getStreakHistoryByDate(startDate: LocalDate, endDate:LocalDate):Flow<List<UserStreakHistory>>{
      return  userDao.getStreakHistoryByDate(startDate,endDate)
    }

}