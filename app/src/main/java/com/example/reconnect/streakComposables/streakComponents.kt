package com.example.reconnect.streakComposables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reconnect.R
import com.example.reconnect.roomUser.StreakViewModel
import java.time.LocalDate
import java.time.YearMonth


@Composable
fun StatsCard(
    modifier: Modifier,
    text1: String,
    text2:String,
    icon: ImageVector,
    iconColor: Color
){
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.White)),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .height(100.dp)
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                modifier = Modifier.size(30.dp).aspectRatio(1f).padding(end=5.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(containerColor = colorResource(R.color.Light_Orange)),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .padding(5.dp),
                    tint = iconColor
                )
            }
            Spacer(modifier=Modifier.width(10.dp))
            Column(modifier=Modifier.weight(1f)
            ) {
                Text(
                    text = text1, style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(R.color.Medium_Gray)
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = text2, style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.Dark_Gray)
                    )
                )

            }
        }


    }
}


@Composable
fun ActivitySection(){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Card(
            modifier = Modifier.size(30.dp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = colorResource(R.color.Light_Orange)),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.CalendarMonth,
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .padding(5.dp),
                tint = colorResource(R.color.Primary_Blue)
            )

        }
        Spacer(modifier = Modifier.width(15.dp))
        Column(
        ) {
            Text(
                text = "Activity", style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = colorResource(R.color.Dark_Gray)
                )
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "This Month ${YearMonth.now().month}", style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = colorResource(R.color.Medium_Gray)
                )
            )

        }
    }
}

@Composable
fun ProgressIndicator(
    streak:Int,
    targetDay:Int,
    targetColor: Color,
    achievement:String,
    stViewModel: StreakViewModel
){
    val streakFlow by stViewModel.streakFlow.collectAsStateWithLifecycle()
    Column(modifier=Modifier.fillMaxWidth()) {
        Row(modifier=Modifier.fillMaxWidth().padding(5.dp), horizontalArrangement = Arrangement.SpaceBetween){
            Text("${targetDay-streakFlow.streak}days",modifier=Modifier.padding(5.dp),
                style=TextStyle(fontSize=12.sp,color=colorResource(R.color.Dark_Gray), fontWeight = FontWeight.Bold))
            Text(text=achievement,modifier=Modifier.padding(5.dp),
                style = TextStyle(fontSize=12.sp,color=colorResource(R.color.Medium_Gray), fontWeight = FontWeight.Normal))

        }
        LinearProgressIndicator(
            progress = { streakFlow.streak / targetDay.toFloat() },
            modifier = Modifier.fillMaxWidth().padding(5.dp).height(5.dp),
            color = targetColor,
            trackColor = Color.LightGray,
        )
    }


}

@Composable
fun CardCircle(circleColor:Color){
    Card(modifier=Modifier.size(15.dp).padding(2.dp),
        colors=CardDefaults.cardColors(containerColor = circleColor),
        shape=RoundedCornerShape(50),
        elevation = CardDefaults.cardElevation(10.dp), content = {})

}

@Composable
fun CalendarSection() {
    Row(
        modifier = Modifier.fillMaxWidth().height(100.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        CardCircle(Color.Black)
        Box(modifier = Modifier.padding(2.dp)) {
            Text(
                text = "NotFound",style = TextStyle( color = colorResource(R.color.Medium_Gray),
                    fontWeight=FontWeight.Normal,
                    fontSize=12.sp,
                    letterSpacing=1.sp,
                    fontFamily = FontFamily.SansSerif
                ))

        }
        CardCircle(Color.Red)
        Box(modifier = Modifier.padding(2.dp)) {
            Text(
                text = "Missed",style = TextStyle( color = colorResource(R.color.Medium_Gray),
                    fontWeight=FontWeight.Normal,
                    fontSize=12.sp,
                    letterSpacing=1.sp,
                    fontFamily = FontFamily.SansSerif
                ))

        }
        CardCircle(colorResource(R.color.Secondary_Green))
        Box(modifier = Modifier.padding(2.dp))
        { Text(text = "Completed",style = TextStyle( color = colorResource(R.color.Medium_Gray),
            fontWeight=FontWeight.Normal,
            fontSize=12.sp,
            letterSpacing=1.sp,
            fontFamily = FontFamily.SansSerif
        )) }
        CardCircle(colorResource(R.color.Orange))
        Box(modifier = Modifier.padding(2.dp))
        { Text(text = "Today",
          style = TextStyle( color = colorResource(R.color.Medium_Gray),
              fontWeight=FontWeight.Normal,
              fontSize=12.sp,
              letterSpacing=1.sp,
              fontFamily = FontFamily.SansSerif
                    )) }

    }
}
@Preview(showSystemUi=true)
@Composable
fun StreakProgressSection(stViewModel: StreakViewModel=viewModel()){
        Card(modifier=Modifier.fillMaxWidth(0.8f),colors=CardDefaults.cardColors(containerColor = colorResource(R.color.White)),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(10.dp)){
            Column(modifier=Modifier.fillMaxSize(),verticalArrangement=Arrangement.spacedBy(8.dp),
                horizontalAlignment=Alignment.CenterHorizontally){
                Box(modifier=Modifier.fillMaxWidth().padding(13.dp,bottom=0.dp,top=13.dp)){
                    Text(text="Next MileStones",modifier=Modifier.align(Alignment.TopStart),
                        style=TextStyle(fontWeight=FontWeight.Bold,fontSize=16.sp))
                }
                ProgressIndicator(7,10,colorResource(R.color.Primary_Blue),"Unlock Calming Sounds",stViewModel)
                ProgressIndicator(7,14,colorResource(R.color.Secondary_Green),"2-week warrior badge",stViewModel)
                ProgressIndicator(7,30,colorResource(R.color.Orange),"Monthly Achievment",stViewModel)
                Spacer(modifier=Modifier.height(20.dp))
            }

        }


}



@Composable
fun CalendarSectionCard(){

        Card(modifier = Modifier
            .fillMaxWidth(0.80f)
            .padding(top = 20.dp),
            colors = CardDefaults.cardColors(containerColor = colorResource(R.color.White)),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Column(modifier=Modifier.fillMaxWidth()) {

                //Activity section
                ActivitySection()

                Spacer(modifier=Modifier.height(20.dp))

                //Calendar Screen
                CalendarScreen()
                Spacer(modifier=Modifier.height(20.dp))
                HorizontalDivider(modifier = Modifier.fillMaxWidth(0.8f).align(Alignment.CenterHorizontally),
                    thickness=2.dp,color=colorResource(R.color.Muted))
                Spacer(modifier=Modifier.height(20.dp))

                //Calendar Last Part Section With References
                CalendarSection()
            }
        }

}
