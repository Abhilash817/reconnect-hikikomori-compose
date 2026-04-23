package com.example.reconnect.workermanager

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.Calendar
import java.util.concurrent.TimeUnit

fun sechduleDailyWorkRequest(context: Context){
    val calendar= Calendar.getInstance()
    val delay=calendar.apply{
      set(Calendar.HOUR_OF_DAY,23)
      set(Calendar.MINUTE,59)
      set(Calendar.SECOND,0)
        add(Calendar.DAY_OF_YEAR,1)
    }.timeInMillis-System.currentTimeMillis()

    val workRequest= PeriodicWorkRequestBuilder<UsageWorker>(1,
        TimeUnit.DAYS
    ).setInitialDelay(delay,TimeUnit.MILLISECONDS)
        .build()
    WorkManager.getInstance(context).
    enqueueUniquePeriodicWork("UsageDataSave",
        ExistingPeriodicWorkPolicy.KEEP,
        workRequest)
}


