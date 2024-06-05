package com.clone.scoutemove.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.clone.scoutemove.R
import com.clone.scoutemove.ui.theme.Purple40
import com.clone.scoutemove.ui.theme.Purple80
import com.clone.scoutemove.ui.theme.PurpleGrey40

@Preview(showSystemUi = true)
@Composable
fun NoInternetScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(60.dp),
            alignment = Alignment.Center,
            painter = painterResource(id = R.drawable.img_no_internet),
            contentDescription = "no internet"
        )
        Text(
            modifier = Modifier.padding(vertical = 5.dp),
            text = "OOPS!! NO INTERNET",
            fontSize = 20.sp,
            color = Color.Red,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium
        )
        Text(
            modifier = Modifier.padding(bottom = 5.dp),
            text = "Please check your internet connection",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = Color.Red
        )
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth(0.8f),
            colors = ButtonDefaults.buttonColors().copy(containerColor = Color.Red)
        ) {
            Text(text = "Try again")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun NoInternetDialog() {
    Box {
        Dialog(onDismissRequest = { }) {
            Column(
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(5.dp))
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.size(40.dp),
                    alignment = Alignment.Center,
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "no internet"
                )
                Text(
                    modifier = Modifier,
                    text = "Oops...",
                    fontSize = 16.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    modifier = Modifier
                        .padding(vertical = 7.dp)
                        .width(200.dp),
                    text = "Please check your internet connection",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
                Button(
                    onClick = { },
                    modifier = Modifier,
                    contentPadding = PaddingValues(horizontal = 30.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors().copy(containerColor = Purple40)
                ) {
                    Text(text = "Ok")
                }
            }
        }
    }
}