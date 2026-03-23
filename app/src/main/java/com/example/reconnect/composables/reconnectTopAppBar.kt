package com.example.reconnect.composables
import androidx.compose.foundation.BorderStroke
import com.example.reconnect.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.ZonedDateTime


@Composable
fun ReconnectTopAppBar(){
    val dayOfTheWeek =remember{
        LocalDate.now().dayOfWeek.toString()
    }
    val month=remember{
        LocalDate.now().month.toString()
    }
    val date=remember{
        LocalDate.now().dayOfMonth.toString()
    }
    Card(modifier = Modifier.fillMaxWidth().height(100.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.White)),
//        border = BorderStroke(5.dp,colorResource(R.color.Muted))
    ){
        Column (modifier=Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Reconnect", style=TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = colorResource(R.color.Secondary_Green)
            ))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text="${dayOfTheWeek},${month}  ${date}", style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            ))
        }

    }


}