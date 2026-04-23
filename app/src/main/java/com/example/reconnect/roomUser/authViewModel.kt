package com.example.reconnect.roomUser

import android.app.Application
import android.util.Log
import androidx.core.text.trimmedLength
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.reconnect.dataStore.ReconnectDataStore
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val auth= FirebaseAuth.getInstance()
    val _isLoggedIn = MutableStateFlow(AuthState())
    val isLoggedIn = _isLoggedIn.asStateFlow()
    private val listener =FirebaseAuth.AuthStateListener{
        _isLoggedIn.value=AuthState(isLoggedIn=it.currentUser!=null)
    }
    init{
        auth.addAuthStateListener(listener)
    }

    val localDb = DatabaseProvider.getDatabase(application)
    val repo = AuthRepository(localDb.userDao())

    val uid=auth.currentUser?.uid.toString()



//    private val reconnectDataStore= ReconnectDataStore(application)

//    var getUserState=reconnectDataStore.getUserStatus().map {
//        if(it){
//            UserState.LoggedIn
//        }
//        else{
//            UserState.LoggedOut
//    }    }.stateIn(
//        viewModelScope,
//        initialValue = UserState.isLoading,
//        started= SharingStarted.WhileSubscribed(5000)
//    )

//firebase Instance


fun signIn(email:String,password:String){
        viewModelScope.launch{
            if(email.isNotBlank()&&password.isNotBlank()) {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        _isLoggedIn.value = AuthState(isLoggedIn = true)
                    } else {
                        _isLoggedIn.value = AuthState(error = it.exception?.message.toString())
                    }

                }
            }
            else {
                _isLoggedIn.value = AuthState(error = "Please fill all the fields")
            }

        }
    }

    fun logOut(){
        viewModelScope.launch{
            auth.signOut()
            _isLoggedIn.value=AuthState(isLoggedIn = false)
        }
    }



    fun createNewUser(name: String, age: Int, email: String, mobileNo: String, password: String) {
        viewModelScope.launch {
            if (name.isNotBlank() && age != 0 && email.isNotBlank() && mobileNo.isNotBlank() && password.isNotBlank()) {
                if (password.trim().length > 8 && mobileNo.trimmedLength()==10) {
                    auth.createUserWithEmailAndPassword(email.trim(), password.trim()).addOnCompleteListener {
                        if (it.isSuccessful) {
                            _isLoggedIn.value = AuthState(isLoggedIn = true)
                        } else {
                            _isLoggedIn.value = AuthState(error = it.exception?.message.toString())
                        }
                    }
                    repo.insertUser(
                        UserInfo(
                            id=uid,
                            name = name.trim(),
                            age = age,
                            email = email.trim(),
                            mobileNo = mobileNo.trim(),
                            password = password.trim()
                        )
                    )
                }
                else{
                    _isLoggedIn.value=AuthState(error = "Invalid Details")
                }
            }
            else{
                _isLoggedIn.value=AuthState(error="Please fill all the fields")
            }
        }
    }
//    fun checkUser(email: String, password: String) = viewModelScope.launch {
//        try {
//            val user = repo.getUserByEmail(email.trim(), password.trim())
//            Log.d("debug","$user")
//
//            if (user) {
//                reconnectDataStore.saveUserStatus(true)
//                _isLoggedIn.value = AuthState(isLoggedIn = true, isLoading = false)
//                Log.d("debug","user was found $user")
//            } else {
//                reconnectDataStore.saveUserStatus(false)
//                Log.d("debug","user was not found $user")
//                _isLoggedIn.value = AuthState(error = "Invalid Details", isLoading = false)
//
//            }
//        } catch (e: Exception) {
//            _isLoggedIn.value=AuthState(error="Something Went Wrong",isLoading = false)
//
//
//        }
//
//    }

//    fun logOut(){
//        viewModelScope.launch {
//            reconnectDataStore.clearUserStatus()
//            _isLoggedIn.value=AuthState(isLoggedIn = false,isLoading = false)
//
//        }
//    }
}