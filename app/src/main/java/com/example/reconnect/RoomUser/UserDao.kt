package com.example.reconnect.RoomUser

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserInfo)

    @Query("SELECT * FROM User WHERE email = :email AND password = :password")
    suspend fun getUserByEmail(email: String,password:String): UserInfo?


}