package com.example.reconnect.composables


import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.key
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
import com.example.reconnect.R


@Composable
fun ReconnectTextFieldComposable(
    textFieldState: MutableState<String>,
    placeholder:String,
    icon: ImageVector,
    iconText:String,
    keyboardActions: KeyboardActions,
    keyboardOptions: KeyboardOptions
    ){
    Column(modifier=Modifier.fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally){
        Row(modifier=Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
            Icon(imageVector =icon,
                contentDescription = null,
                tint = colorResource(R.color.Form_Label_Icons),
                modifier=Modifier.size(24.dp)
                )
            Spacer(modifier=Modifier.width(5.dp))
            Text(text=iconText,
                style= TextStyle(
                    fontSize=14.sp,
                    fontFamily=FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(modifier=Modifier.height(5.dp))
        OutlinedTextField(
            value=textFieldState.value,
            onValueChange={
                textFieldState.value=it
            },
            modifier=Modifier.fillMaxWidth(),
            placeholder={
                Text(text=placeholder)
            },
            keyboardActions=keyboardActions,
        keyboardOptions=keyboardOptions,
            shape= RoundedCornerShape(20.dp),
            colors= OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(R.color.Form_Label_Icons),
                unfocusedBorderColor = colorResource(R.color.Muted),
                focusedContainerColor = colorResource(R.color.Input_Fields),
                unfocusedContainerColor = colorResource(R.color.Input_Fields),

            )
            )


    }




}
