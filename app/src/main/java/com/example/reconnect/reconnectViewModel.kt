package com.example.reconnect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ReconnectViewModel: ViewModel() {
    val _timer = MutableStateFlow(ReconnectClock(600))       //60*10=600seconds
    val timer=_timer.asStateFlow()
var timeJob:kotlinx.coroutines.Job?=null

    fun decreaseTimer() {
        timeJob?.cancel()
      timeJob=  viewModelScope.launch {


           while(_timer.value.CurrentSeconds>0){
                delay(1000)
                _timer.value = _timer.value.copy(CurrentSeconds = _timer.value.CurrentSeconds - 1)
            }
        }
    }
    private fun pauseTimer(){
        timeJob?.cancel()
    }

    fun doAction(action: ClockScreenAction) {
        when (action) {
            is ClockScreenAction.StartTimer -> {
                decreaseTimer()
            }

            is ClockScreenAction.PauseTimer -> {
                pauseTimer()
            }

            is ClockScreenAction.ResetTimer -> {
                pauseTimer()
                _timer.value = _timer.value.copy(CurrentSeconds = 600)
            }
        }
    }
}