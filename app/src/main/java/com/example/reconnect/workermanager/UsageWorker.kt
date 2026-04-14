package com.example.reconnect.workermanager

import android.content.Context
import androidx.compose.material3.DatePicker
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.reconnect.RoomUser.AuthRepository
import com.example.reconnect.RoomUser.DatabaseProvider
import com.example.reconnect.RoomUser.UserUsageInsights
import com.example.reconnect.usageManager.behaviorScore
import com.example.reconnect.usageManager.getStartOfToday
import com.example.reconnect.usageManager.todayScreenTime
import com.example.reconnect.usageManager.unlockCount
import java.time.LocalDate
import java.util.Date

class UsageWorker(context: Context, workerParams: WorkerParameters):
    CoroutineWorker(context,workerParams){
    override suspend fun doWork(): Result {
        val localDb= DatabaseProvider.getDatabase(applicationContext)
        val userDao=localDb.userDao()
        val repo= AuthRepository(userDao)
        val year= LocalDate.now().year
        val month=LocalDate.now().month
        val day=LocalDate.now().dayOfMonth
        val date="$day-$month-$year"
        val screenTime= todayScreenTime(applicationContext)
        val unlockCount=unlockCount(applicationContext,
            getStartOfToday(),System.currentTimeMillis())
        val behaviorScore= behaviorScore(todayScreenTime(applicationContext)/1000*60*60,unlockCount)
        repo.insertActivity(UserUsageInsights(date, screenTime,unlockCount,behaviorScore))

        return Result.success()
    }

}