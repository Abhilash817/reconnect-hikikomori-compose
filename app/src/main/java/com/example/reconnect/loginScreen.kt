package com.example.reconnect


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.Email

import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.reconnect.RoomUser.AuthViewModel
import com.example.reconnect.composables.ReconnectTextFieldComposable


@Composable
fun LoginScreen(vm: AuthViewModel, paddingValues: PaddingValues=PaddingValues(0.dp), onNavigateToSignUp:()->Unit){
    val password = remember{mutableStateOf("")}
    val email = remember{mutableStateOf("")}
    val keyboardController= LocalSoftwareKeyboardController.current
    val context=LocalContext.current
    val isLoggedIn by vm.isLoggedIn.collectAsStateWithLifecycle()


        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
                .background(color = colorResource(R.color.Warm_Beige)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth().height(100.dp), colors =
                    CardDefaults.cardColors(containerColor = colorResource(R.color.White))
            )
            {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start
                ){
                    Text(
                        text = "Reconnect", style = TextStyle(
                            fontSize = 24.sp, fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic
                        ),
                        modifier = Modifier.padding(
                            start = 10.dp,
                            bottom = 0.dp,
                            end = 10.dp,
                            top = 10.dp
                        )
                    )
                    Text(
                        text = "Welcome Back", style = TextStyle(
                            fontSize = 14.sp, fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Italic,
                            color = colorResource(R.color.Medium_Gray)
                        ),
                        modifier = Modifier.padding(
                            top = 5.dp,
                            start = 10.dp,
                            bottom = 10.dp,
                            end = 10.dp
                        )
                    )

                }
            }
            HorizontalDivider(thickness = 2.dp, color = colorResource(R.color.Muted))
            Spacer(modifier = Modifier.height(40.dp))
            Card(
                modifier = Modifier.size(80.dp),
                colors = CardDefaults.cardColors(containerColor = colorResource(R.color.Iron_Circle_Background)),
                shape = RoundedCornerShape(100.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Login,
                    contentDescription = null, tint = colorResource(R.color.Iron_Circle_Foreground),
                    modifier = Modifier.size(70.dp).padding(12.dp)
                )

            }
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Welcome back",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Sign in to continue to your wellness journey",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic,
                    color = colorResource(R.color.Medium_Gray)
                )
            )
            Spacer(modifier = Modifier.height(15.dp))
            Card(
                modifier = Modifier.fillMaxWidth(0.8f).height(250.dp),
                colors = CardDefaults.cardColors(containerColor = colorResource(R.color.White)),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(10.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    ReconnectTextFieldComposable(
                        textFieldState = email,
                        placeholder = "your@gmail.com",
                        icon = Icons.Default.Email,
                        iconText = "Email",
                        keyboardActions = KeyboardActions(
                            onAny = { keyboardController?.hide() }
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        )
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    ReconnectTextFieldComposable(
                        textFieldState = password,
                        placeholder = "Password",
                        icon = Icons.Filled.Password,
                        iconText = "Password",
                        keyboardActions = KeyboardActions(
                            onDone = { keyboardController?.hide()
                                if(email.value.isNotBlank()&&password.value.isNotBlank()) {
                                    if(isValidEmail(email.value)&&password.value.length>8) {
                                        vm.checkUser(
                                            email.value.trim(),
                                            password.value.trim()
                                        ).isCompleted
                                    }
                                    else{
                                        Toast.makeText(context,"Invalid Details",Toast.LENGTH_LONG).show()

                                    }
                                }
                                else{
                                    Toast.makeText(context,"Please fill all the fields",Toast.LENGTH_SHORT).show()
                                }
                            }
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                modifier = Modifier.fillMaxWidth(0.8f).height(50.dp).clickable {
                    if(email.value.isNotBlank()&&password.value.isNotBlank()) {
                        keyboardController?.hide()
                        if(isValidEmail(email.value)&&password.value.length>8) {
                            vm.checkUser(email.value.trim(), password.value.trim()).isCompleted
                        }
                        else{
                            Toast.makeText(context,"Invalid Details",Toast.LENGTH_LONG).show()
                        }
                    }
                    else{
                        Toast.makeText(context,"Please fill all the fields",Toast.LENGTH_SHORT).show()
                    }
                },
                colors = CardDefaults.cardColors(containerColor = colorResource(R.color.Form_Label_Icons)),
                shape = RoundedCornerShape(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Login,
                        contentDescription = null,
                        tint = colorResource(R.color.White),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Sign In",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.White),
                            fontFamily = FontFamily.SansSerif
                        )
                    )
                }

            }
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                modifier = Modifier.fillMaxWidth(0.8f).height(50.dp).clickable { onNavigateToSignUp.invoke() },
                colors = CardDefaults.cardColors(containerColor = colorResource(R.color.White)),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Create New Account",
                        style = TextStyle(
                            fontSize = 16.sp, fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif
                        )
                    )
                }

            }

        }
    }



fun isValidEmail(email:String):Boolean{
    val newEmail=email.trim()
    return newEmail.isNotBlank()&& android.util.Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()
}
