package com.example.reconnect.RoomUser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid.Companion.random

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    val _isLoggedIn=MutableStateFlow(false)
    val isLoggedIn=_isLoggedIn.asStateFlow()

    val localDb=DatabaseProvider.getDatabase(application).userDao()


    fun createNewUser(name:String, age:Int, email:String, mobileNo:String, password:String) {
        viewModelScope.launch {
            localDb.insertUser(UserInfo( name=name, age=age,email= email,mobileNo= mobileNo,password= password))
        }
    }

    fun checkUser(email: String, password: String)=viewModelScope.launch{
            val user=localDb.getUserByEmail(email,password)
            if(user!=null){
                _isLoggedIn.value=true
            }
            else{
                _isLoggedIn.value=false
            }
        }

    }
