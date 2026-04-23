package com.example.reconnect.roomUser

import android.content.Context
import androidx.core.view.WindowInsetsCompat
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [UserInfo::class,UserStreak::class,
    UserUsageInsights::class,UserStreakHistory::class], version = 12, exportSchema = false)
@TypeConverters(Converters::class)
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

