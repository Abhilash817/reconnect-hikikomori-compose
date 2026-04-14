package com.example.reconnect.RoomUser

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [UserInfo::class,UserStreak::class,
    UserUsageInsights::class], version = 5, exportSchema = false)
abstract class AppDataBase:RoomDatabase(){
    abstract fun userDao(): UserDao
}

object DatabaseProvider{
    private var INSTANCE: AppDataBase? = null
    fun getDatabase(context: Context): AppDataBase {
        return INSTANCE?:synchronized(this){
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "app_database"
            ).fallbackToDestructiveMigration()
                .build()
            INSTANCE=instance
            instance
        }

    }


}

