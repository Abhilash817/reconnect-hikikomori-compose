package com.example.reconnect


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reconnect.RoomUser.AuthViewModel
import com.example.reconnect.composables.ReconnectTextFieldComposable


@Composable
fun SignUpScreen(vm: AuthViewModel, paddingValues: PaddingValues=PaddingValues(0.dp), onNavigateToLogin:()->Unit={}) {
    val name = remember { mutableStateOf("") }
    val age = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val mobileNo = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val keyboardController=LocalSoftwareKeyboardController.current
    val context=LocalContext.current


    LazyColumn(
        modifier = Modifier.fillMaxWidth().padding(paddingValues)
            .background(color = colorResource(R.color.Warm_Beige)).clickable{
                keyboardController?.hide()
            },
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            Card(
                modifier = Modifier.fillMaxWidth().height(100.dp),
                colors = CardDefaults.cardColors(containerColor = colorResource(R.color.White))
            ) {
                Row(
                    modifier = Modifier.fillMaxSize().padding(10.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.size(40.dp)
                            .background(color = colorResource(R.color.Muted))
                            .clip(RoundedCornerShape(20.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                                .clickable { onNavigateToLogin.invoke() })

                    }

                    Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Reconnect", style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.SansSerif,
                            )
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = "Create Your Account", style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = FontFamily.SansSerif,
                                color = colorResource(R.color.Medium_Gray)
                            )
                        )
                    }
                }


            }

        }
        item{
            Spacer(modifier=Modifier.height(10.dp))
        }

        item {
            Card(
                modifier = Modifier.size(80.dp),
                colors = CardDefaults.cardColors(containerColor = colorResource(R.color.Iron_Circle_Background)),
                shape = RoundedCornerShape(50)
            ) {
                Icon(
                    imageVector = Icons.Default.PersonAdd,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize().padding(15.dp),
                    tint = colorResource(R.color.Sign_Up_Button)
                )

            }
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Text(
                text = "Join Reconnect", style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                )
            )
        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            Text(
                text = "Start your journey to mindful living", style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.SansSerif,
                    color = colorResource(R.color.Medium_Gray)
                )
            )

        }
        item {
            Spacer(modifier = Modifier.height(20.dp))

        }
        item {

            Card(
                modifier = Modifier.fillMaxWidth(0.9f).height(550.dp),
                colors = CardDefaults.cardColors(containerColor = colorResource(R.color.White)),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
                    Spacer(modifier = Modifier.height(10.dp))
                    ReconnectTextFieldComposable(
                        textFieldState = name,
                        placeholder = "Full Name",
                        icon = Icons.Default.Person,
                        iconText = "Full Name",
                        keyboardActions = KeyboardActions { keyboardController?.hide() },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Text,
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    ReconnectTextFieldComposable(
                        textFieldState = age,
                        placeholder = "Enter your Age",
                        icon = Icons.Default.CalendarToday,
                        iconText = "Age",
                        keyboardActions = KeyboardActions(
                            onAny = {
                                keyboardController?.hide()
                            }
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Number
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    ReconnectTextFieldComposable(
                        textFieldState = email,
                        placeholder = "yours@email.com",
                        icon = Icons.Default.Email,
                        iconText = "Email",
                        keyboardActions = KeyboardActions {
                            keyboardController?.hide()
                            if(!validEmailOrNOt(email.value)){
                                Toast.makeText(context,"Invalid Email",Toast.LENGTH_SHORT).show()
                            } },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    ReconnectTextFieldComposable(
                        textFieldState = mobileNo,
                        placeholder = "Enter your Mobile NO",
                        icon = Icons.Default.Phone,
                        iconText = "Mobile:No",
                        keyboardActions = KeyboardActions {
                            keyboardController?.hide()
                            if(mobileNo.value.length!=10){
                                Toast.makeText(context,"Invalid Mobile Number",Toast.LENGTH_SHORT).show()
                            }  },

                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Number
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    ReconnectTextFieldComposable(
                        textFieldState = password,
                        placeholder = "Chose  a strong password",
                        icon = Icons.Default.Security,
                        iconText = "Password",
                        keyboardActions = KeyboardActions(
                            onDone= {
                                if (password.value.length > 8) {
                                    keyboardController?.hide()
                                    if (email.value.isNotBlank() && password.value.isNotBlank() && mobileNo.value.isNotBlank() && name.value.isNotBlank() && age.value.isNotBlank()) {
                                       if(isValidEmail(email.value)&&mobileNo.value.length==10) {
                                           vm.createNewUser(
                                               name.value.trim(),
                                               age.value.toInt(),
                                               email.value.trim(),
                                               mobileNo.value.trim(),
                                               password.value.trim()
                                           )
                                           Toast.makeText(
                                               context,
                                               "Account Created Successfully Go Back to LoginScreen",
                                               Toast.LENGTH_SHORT
                                           ).show()
                                       }
                                        else{
                                            Toast.makeText(context,"Invalid Email or Mobile Number",Toast.LENGTH_SHORT).show()
                                       }
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Please fill all the fields",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                                else{
                                    Toast.makeText(context,"Password must be least 8 characters long",Toast.LENGTH_SHORT).show()
                                }
                            }
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Password
                        )
                    )
                    Spacer(modifier=Modifier.height(10.dp))
                    Text(
                        text = "Password must be least 8 characters long", style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.SansSerif,
                            color = colorResource(R.color.Medium_Gray)
                        )
                    )

                }

            }
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            Card(
                modifier = Modifier.fillMaxWidth(0.8f).height(50.dp).clickable {
                    keyboardController?.hide()
                    if(email.value.isNotBlank()&&password.value.isNotBlank()&& mobileNo.value.isNotBlank()&&name.value.isNotBlank()&&age.value.isNotBlank()) {
                        if (validEmailOrNOt(email.value) && mobileNo.value.length == 10 && password.value.length > 8) {
                            vm.createNewUser(
                                name.value,
                                age.value.toInt(),
                                email.value,
                                mobileNo.value,
                                password.value
                            )
                            Toast.makeText(
                                context,
                                "Account Created Successfully Go Back to LoginScreen",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        else{
                            Toast.makeText(context,"Please Check the Details",Toast.LENGTH_LONG).show()
                        }
                    }
                    else{
                        Toast.makeText(context,"Please fill all the fields",Toast.LENGTH_SHORT).show()
                    }
                },
                colors = CardDefaults.cardColors(containerColor = colorResource(R.color.Sign_Up_Button)),
                shape = RoundedCornerShape(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.PersonAdd,
                        contentDescription = null,
                        tint = colorResource(R.color.White),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Create Account",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.White),
                            fontFamily = FontFamily.SansSerif
                        )
                    )
                }


            }

        }

    }
}

fun validEmailOrNOt(email:String):Boolean{
    val newEmail=email.trim()
   return newEmail.isNotBlank()&& android.util.Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()
}