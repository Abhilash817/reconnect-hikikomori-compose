package com.example.reconnect.settingcomposables


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reconnect.R

@Preview(showSystemUi=true)
@Composable
fun SettingTopBar(profileScreen:()->Unit={}){
   Card(modifier=Modifier.fillMaxWidth().height(100.dp).
   padding(top=10.dp,bottom=0.dp,start=10.dp,end=10.dp),
       colors= CardDefaults.cardColors(colorResource(R.color.White))
   ){
       Row(modifier=Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween){
           Column(){
               Text(text="Settings",style= TextStyle(
                   color = colorResource(R.color.Dark_Gray),
                   fontSize = 24.sp,
                   fontWeight = FontWeight.Bold
               ))
                   Spacer(modifier=Modifier.height(5.dp))
               Text(text="Manage your Preferences",
                   style=TextStyle(
                       color=colorResource(R.color.Medium_Gray),
                       fontSize = 14.sp,
                       fontWeight = FontWeight.Normal
                   ))

           }
           FloatingActionButton(onClick = {profileScreen.invoke()  },
               modifier=Modifier.size(40.dp),
               containerColor = colorResource(R.color.Muted),
               shape=RoundedCornerShape(50)) {
               Icon(imageVector = Icons.Default.Person,
                   contentDescription = null,
                   modifier=Modifier.fillMaxSize().padding(2.dp),
                   tint=colorResource(R.color.Iron_Circle_Foreground))

           }
       }

   }
    HorizontalDivider(modifier=Modifier.fillMaxWidth(),thickness = 5.dp, color = colorResource(R.color.Muted))
}