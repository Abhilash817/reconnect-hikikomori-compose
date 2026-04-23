package com.example.reconnect.dataStore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class ReconnectDataStore(private val context: Context){
    private val Context.dataStore by preferencesDataStore("ReconnectDataStore")

    private val userKey= booleanPreferencesKey("user_status")


    suspend fun saveUserStatus(isLoggedIn:Boolean){
        context.dataStore.edit{prefs->
            prefs[userKey]=isLoggedIn
        }
    }
    suspend fun clearUserStatus(){
        context.dataStore.edit{
            it.clear()
        }
    }

    fun getUserStatus(): Flow<Boolean>{
        return context.dataStore.data.map{
            it[userKey]?:false
        }
    }

}
