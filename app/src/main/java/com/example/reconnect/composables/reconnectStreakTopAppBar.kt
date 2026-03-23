package com.example.reconnect.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.reconnect.R




@Composable
fun ReconnectStreakTopAppBar() {
    Card(modifier=Modifier.fillMaxWidth().requiredHeight(300.dp).padding(0.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.Light_Orange))) {
        Column(modifier=Modifier.fillMaxSize().padding(0.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Card(modifier = Modifier.size(45.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(containerColor = colorResource(R.color.Light_Orange)),
                elevation = CardDefaults.cardElevation(10.dp)) {
                Icon(
                    imageVector = Icons.Filled.LocalFireDepartment,
                    contentDescription = null,
                    modifier = Modifier.size(45.dp).padding(5.dp),
                    tint= colorResource(R.color.Orange)
                )
            }
            Spacer(modifier=Modifier.height(10.dp))
            Text(text="7 days",
                style = TextStyle(fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.Orange)))
            Spacer(modifier=Modifier.height(10.dp))
            Text(text="Current Streak",
                style=TextStyle(fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                , color = colorResource(R.color.Orange)
                    ))

        }
    }
}