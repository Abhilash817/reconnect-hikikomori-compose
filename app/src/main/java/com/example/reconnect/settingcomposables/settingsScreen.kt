package com.example.reconnect.settingcomposables


import android.app.usage.UsageEvents
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import android.view.RoundedCorner
import android.widget.ToggleButton
import com.example.reconnect.R
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MobileFriendly
import androidx.compose.material.icons.filled.ModeNight
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PermDeviceInformation
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Bullet
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.getSystemService
import java.util.Calendar


@Preview(showSystemUi=true)
@Composable
fun SettingsScreen(onBack:()->Unit={},
                   paddingValues: PaddingValues=PaddingValues(0.dp),
                   profileScreen:()->Unit={}) {
    val Context =LocalContext.current
    var checkedState by remember{
        mutableStateOf(false)
    }

    var grantState by remember{
        mutableStateOf(false)
    }
    BackHandler {
        onBack()
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        topBar = { SettingTopBar(profileScreen) },
        containerColor = colorResource(R.color.White)
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 3.dp,
                    color = colorResource(R.color.Muted)
                )
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.80f),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(colorResource(R.color.White)),
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp),
                            shape = RoundedCornerShape(
                                topStart = 20.dp,
                                topEnd = 20.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 0.dp
                            ),
                            colors = CardDefaults.cardColors(colorResource(R.color.Iron_Circle_Background)),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {

                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                FloatingActionButton(
                                    modifier = Modifier.size(50.dp),
                                    onClick = {},
                                    containerColor = colorResource(R.color.Iron_Circle_Background),
                                    shape = RoundedCornerShape(50)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.MobileFriendly,
                                        contentDescription = null,
                                        tint = colorResource(R.color.Iron_Circle_Foreground),
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(10.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(5.dp))
                                Column() {
                                    Text(
                                        text = "Usage Access", style = TextStyle(
                                            color = colorResource(R.color.Dark_Gray),
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            fontFamily = FontFamily.SansSerif
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Text(
                                        text = "Allow reconnect to track your screen time to provide personalised insights",
                                        style = TextStyle(
                                            color = colorResource(R.color.Medium_Gray),
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Normal,
                                            fontFamily = FontFamily.SansSerif
                                        )
                                    )
                                }


                            }

                        }
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(),
                            thickness = 3.dp,
                            color = colorResource(R.color.Muted)
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                       if(grantState){
                           UserGrantedSection()
                       }
                        else{

                           BulletRow(text="Monitor Daily Screen Time")
                           BulletRow(text="Track App Usage Patterns")
                           BulletRow(text="Set Daily Limits")

                           FilledTonalButton(onClick={
                               grantState=true
                               openUsageAccessSettings(context=Context)

                           },
                               modifier=Modifier
                                   .fillMaxWidth()
                                   .height(70.dp)
                                   .padding(10.dp),
                               shape=RoundedCornerShape(20.dp),
                               colors= ButtonDefaults.filledTonalButtonColors(
                                   containerColor = colorResource(R.color.Iron_Circle_Foreground),
                               )) {


                               Text(text="Grant Permission",style=TextStyle(
                                   fontSize=18.sp,
                                   color=colorResource(R.color.White),
                                   fontWeight = FontWeight.Bold,
                                   fontFamily = FontFamily.SansSerif
                               ))
                           }
                           Text(text="Your Data Stays private and is never shared",
                               style=TextStyle(
                                   fontSize=14.sp,
                                   color=colorResource(R.color.Medium_Gray),
                                   fontWeight = FontWeight.Normal,
                                   fontFamily = FontFamily.SansSerif),
                               modifier=Modifier.padding(start=20.dp,end=15.dp))

                           Spacer(modifier=Modifier.height(10.dp))

                       }



                        }





                    }
                }
            item{
                Spacer(modifier=Modifier.height(20.dp))
            }


            //App Settings Section card starts here


            item{
                Card(modifier=Modifier
                    .fillMaxWidth(0.80f)
                    .height(200.dp),
                    colors=CardDefaults.cardColors(containerColor = colorResource(R.color.White)),
                    shape= RoundedCornerShape(20.dp),
                    elevation=CardDefaults.cardElevation(20.dp)){
                    Column(modifier=Modifier.fillMaxSize()){
                        Text(text="App Settings",
                            style=TextStyle(fontSize=20.sp,
                                fontWeight=FontWeight.Bold,
                                color=colorResource(R.color.Dark_Gray),
                                fontFamily = FontFamily.SansSerif),
                            modifier=Modifier.padding(10.dp))
                        HorizontalDivider(modifier=Modifier.fillMaxWidth(),
                            thickness = 3.dp,
                            color = colorResource(R.color.Muted))
                        Row(modifier=Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 10.dp, top = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically){

                            Icon(imageVector=Icons.Default.Notifications,
                                contentDescription=null,
                                tint=colorResource(R.color.Terracotta),
                                modifier=Modifier.size(30.dp))

                            Spacer(modifier=Modifier.width(10.dp))
                            Column(){
                                Text(text="Notifications",
                                    style=TextStyle(fontSize=18.sp,
                                       fontWeight=FontWeight.Bold,
                                        fontFamily=FontFamily.SansSerif
                                    ))
                                Spacer(modifier=Modifier.height(5.dp))
                                Text(text="Enabled",
                                    style=TextStyle(fontSize=14.sp,
                                        color=colorResource(R.color.Medium_Gray),
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight=FontWeight.Normal
                                    ))

                            }
                            Switch(checkedState,
                                onCheckedChange = {
                                    checkedState=it
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor=colorResource(R.color.Sign_Up_Button),
                                    uncheckedThumbColor = colorResource(R.color.Medium_Gray),
                                    uncheckedIconColor = colorResource(R.color.White),
                                    uncheckedBorderColor = colorResource(R.color.Gradient_Green),
                                    checkedBorderColor = colorResource(R.color.Gradient_Green)
                                ),
                                modifier=Modifier
                                    .size(50.dp)
                                    .background(color = colorResource(R.color.White)))

                        }
                        HorizontalDivider(modifier=Modifier.fillMaxWidth(),
                            thickness = 3.dp,
                            color = colorResource(R.color.Muted)
                        )

                        Row(modifier=Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 20.dp, end = 10.dp, top = 10.dp, bottom = 10.dp
                            ),
                            horizontalArrangement=Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically){
                            Icon(imageVector=Icons.Default.ModeNight,
                                contentDescription=null,
                                tint=colorResource(R.color.Iron_Circle_Foreground),
                                modifier=Modifier.size(30.dp))

                            Column(){
                                Text(text="Sleep Mode",style=TextStyle(
                                    fontWeight=FontWeight.Bold,
                                    fontSize=18.sp,
                                    fontFamily=FontFamily.SansSerif
                                ))

                                Spacer(modifier=Modifier.height(5.dp))
                                Text(text="10PM-7AM",style=TextStyle(
                                    fontSize=14.sp,
                                    color=colorResource(R.color.Medium_Gray),
                                    fontFamily = FontFamily.SansSerif
                                ))
                                Spacer(modifier=Modifier.height(5.dp))
                            }
                            IconButton(
                                onClick = {},
                                modifier = Modifier.size(30.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                    contentDescription = "Go back",
                                    modifier = Modifier.size(18.dp), // Set a specific size for the icon itself
                                    tint = colorResource(R.color.Medium_Gray) // Optional: add a tint
                                )
                            }



                        }
                       




                    }


                }
            }
            item{
                Spacer(modifier=Modifier.height(20.dp))
            }
            //About Section card Starts here

            item{
                Card(modifier=Modifier
                    .fillMaxWidth(0.80f)
                    .height(200.dp),
                    colors=CardDefaults.cardColors(containerColor = colorResource(R.color.White)),
                    shape= RoundedCornerShape(20.dp),
                    elevation=CardDefaults.cardElevation(20.dp)){
                    Column(modifier=Modifier.fillMaxSize()){
                        Text(text="About",
                            style=TextStyle(fontSize=20.sp,
                                fontWeight=FontWeight.Bold,
                                color=colorResource(R.color.Dark_Gray),
                                fontFamily = FontFamily.SansSerif),
                            modifier=Modifier.padding(10.dp))
                        HorizontalDivider(modifier=Modifier.fillMaxWidth(),
                            thickness = 3.dp,
                            color = colorResource(R.color.Muted))
                        Row(modifier=Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 10.dp, top = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically){

                            Icon(imageVector=Icons.Default.Info,
                                contentDescription=null,
                                tint=colorResource(R.color.Secondary_Green),
                                modifier=Modifier.size(30.dp))

                            Spacer(modifier=Modifier.width(10.dp))
                            Column(){
                                Text(text="How it Works ",
                                    style=TextStyle(fontSize=18.sp,
                                        fontWeight=FontWeight.Bold,
                                        fontFamily=FontFamily.SansSerif
                                    ))
                                Spacer(modifier=Modifier.height(5.dp))
                                Text(text="Learn About Reconnect",
                                    style=TextStyle(fontSize=14.sp,
                                        color=colorResource(R.color.Medium_Gray),
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight=FontWeight.Normal
                                    ))

                            }
                            IconButton(
                                onClick = { /* Handle navigation back */ },
                                modifier = Modifier.size(30.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                    contentDescription = "Go back",
                                    modifier = Modifier.size(18.dp), // Set a specific size for the icon itself
                                    tint = colorResource(R.color.Medium_Gray) // Optional: add a tint
                                )
                            }


                        }
                        HorizontalDivider(modifier=Modifier.fillMaxWidth(),
                            thickness = 3.dp,
                            color = colorResource(R.color.Muted)
                        )

                        Row(modifier=Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 20.dp, end = 10.dp, top = 10.dp, bottom = 10.dp
                            ),
                            horizontalArrangement=Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically){
                            Icon(imageVector=Icons.Default.Security,
                                contentDescription=null,
                                tint=colorResource(R.color.Iron_Circle_Foreground),
                                modifier=Modifier.size(30.dp))

                            Column(){
                                Text(text="Private Policy",style=TextStyle(
                                    fontWeight=FontWeight.Bold,
                                    fontSize=18.sp,
                                    fontFamily=FontFamily.SansSerif
                                ))

                                Spacer(modifier=Modifier.height(5.dp))
                                Text(text="Your Data Is Safe",style=TextStyle(
                                    fontSize=14.sp,
                                    color=colorResource(R.color.Medium_Gray),
                                    fontFamily = FontFamily.SansSerif
                                ))
                                Spacer(modifier=Modifier.height(5.dp))
                            }

                            IconButton(
                                onClick = { /* Handle navigation back */ },
                                modifier = Modifier.size(30.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                    contentDescription = "Go back",
                                    modifier = Modifier.size(18.dp), // Set a specific size for the icon itself
                                    tint = colorResource(R.color.Medium_Gray) // Optional: add a tint
                                )
                            }



                        }





                    }


                }
            }

            item{
                Spacer(modifier=Modifier.height(20.dp))
            }

            //Tail Section Stats Here

            item{
                Text(text="Reconnectv1.0.0",
                    style=TextStyle(
                        fontSize=14.sp,
                        color=colorResource(R.color.Medium_Gray),
                        fontWeight = FontWeight.Normal,
                        fontFamily=FontFamily.SansSerif
                    ))
                Spacer(modifier=Modifier.height(5.dp))
                Text(text="Build With Care for your Wellbeing",
                    style=TextStyle(
                        fontSize=14.sp,
                        color=colorResource(R.color.Medium_Gray),
                        fontWeight = FontWeight.Normal,
                        fontFamily=FontFamily.SansSerif
                    ))




            }

            }
        }
    }


@Composable
fun BulletRow(
    text:String
) {
    Row(modifier=Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(start = 20.dp, top = 10.dp), horizontalArrangement = Arrangement.Start){
        Card(
            modifier = Modifier.size(20.dp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(colorResource(R.color.Iron_Circle_Background))
        ) {
            Card(modifier=Modifier
                .size(20.dp)
                .padding(5.dp),shape=CircleShape,
                colors = CardDefaults.cardColors(colorResource(R.color.Iron_Circle_Foreground))) {}

        }
        Spacer(modifier=Modifier.width(10.dp))
        Text(text=text,style= TextStyle(
            fontSize=16.sp,
            color=colorResource(R.color.Dark_Gray),
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.SansSerif,
            textAlign = TextAlign.Center
        ))
    }

}





@Composable
fun UserGrantedSection(){
    Row(modifier=Modifier.fillMaxWidth().padding(10.dp),
        verticalAlignment = Alignment.CenterVertically){
        Icon(
            imageVector = Icons.Default.PermDeviceInformation,
            contentDescription = null,
            tint = colorResource(R.color.Secondary_Green),
            modifier = Modifier.size(30.dp)
        )
        Spacer(modifier=Modifier.width(5.dp))
        Column(){
            Text("Access Granted",
                style=TextStyle(
                    fontSize=16.sp,
                    fontFamily=FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    color=colorResource(R.color.Secondary_Green),
                ))
            Spacer(modifier=Modifier.height(5.dp))
            Text("Tracking Screen Time",
                style=TextStyle(
                    fontSize=12.sp,
                    fontFamily=FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color=colorResource(R.color.Medium_Gray),
                ))

        }

    }
}


fun openUsageAccessSettings(context: Context) {
    val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
    context.startActivity(intent)
}






