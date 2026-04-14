package com.example.reconnect.RoomUser

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow


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

}