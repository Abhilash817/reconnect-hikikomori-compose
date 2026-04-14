package com.example.reconnect.usageManager

import android.app.usage.UsageEvents
import android.app.usage.UsageStatsManager
import android.content.Context
import android.util.Log
import java.util.Calendar

val getStartOfYesterday={
    val calendar=Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR,-1)
    calendar.set(Calendar.HOUR_OF_DAY,0)
    calendar.set(Calendar.MINUTE,0)
    calendar.set(Calendar.SECOND,0)
    calendar.set(Calendar.MILLISECOND,0)
    calendar.timeInMillis
}

fun usageStats(context: Context,startTime:Long,endTime:Long):Long{
    val usm=context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
    val events=usm.queryEvents(startTime,endTime)
    val event= UsageEvents.Event()

    var lastStart: Long = 0
    var totalTime: Long = 0
    while (events.hasNextEvent()) {
        events.getNextEvent(event)
        when (event.eventType) {
            UsageEvents.Event.SCREEN_INTERACTIVE-> {
                lastStart = event.timeStamp
            }
            UsageEvents.Event.SCREEN_NON_INTERACTIVE -> {
                if (lastStart != 0L) {
                    totalTime += (event.timeStamp - lastStart)
                    lastStart = 0
                }
            }
        }
    }
    return totalTime
}

fun behaviorScore(totalScreenTime:Long,unlockCount:Int):Int{
    val score=totalScreenTime/unlockCount
    return score.toInt()
}


fun unlockCount(context: Context,startTime:Long,endTime:Long):Int{
    val usm=context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
    val events=usm.queryEvents(startTime,endTime)
    val event= UsageEvents.Event()
    var count=0
    var unlockCount=0
    while(events.hasNextEvent()){
        events.getNextEvent(event)
        when(event.eventType) {

            UsageEvents.Event.SCREEN_INTERACTIVE -> {
                count++
            }

            }
        }
    return count

    }




fun getStartOfToday():Long{
    val calendar= Calendar.getInstance()
    calendar.set(Calendar.HOUR_OF_DAY,0)
    calendar.set(Calendar.MINUTE,0)
    calendar.set(Calendar.SECOND,0)
    calendar.set(Calendar.MILLISECOND,0)

    return calendar.timeInMillis
}

fun todayScreenTime(context: Context):Long{
    return usageStats(context,getStartOfToday(),System.currentTimeMillis())
}

fun yesterdayScreenTime(context:Context):Long{
    val a=usageStats(context,getStartOfYesterday(),getStartOfToday())
    return a
}