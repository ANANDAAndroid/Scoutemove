package com.clone.scoutemove.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import com.clone.scoutemove.utils.AppSettings
import com.clone.scoutemove.utils.annotatedString
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(dataStore: DataStore<AppSettings>, onTextClick: () -> Unit, onClick: () -> Unit) {
    var userName by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(colors = listOf(Color.Red, Color.White)))
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = userName,
            onValueChange = {
                userName = it
            },
            label = {
                Text(text = "Username", color = Color.Black)
            },
            colors = TextFieldDefaults.colors().copy(
                unfocusedContainerColor = Color.LightGray,
                focusedContainerColor = Color.LightGray
            )
        )
        TextField(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = "Password", color = Color.Black)
            },
            colors = TextFieldDefaults.colors().copy(
                unfocusedContainerColor = Color.LightGray,
                focusedContainerColor = Color.LightGray
            )
        )
        Button(
            onClick = {
                scope.launch {
                    val data = dataStore.data.first()
                    if (userName == data.name && password == data.password) {
                        Toast.makeText(
                            context,
                            "Login Successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        onClick()
                    } else {
                        Toast.makeText(
                            context,
                            "Seems you are not registered or Invalid credentials",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }


            }, modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors().copy(containerColor = Color.Red)
        ) {
            Text(text = "Login", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        ClickableText(modifier = Modifier
            .padding(top = 15.dp), text = annotatedString, onClick = { offset ->
            annotatedString.getStringAnnotations(offset, offset).firstOrNull()?.let {
                onTextClick()
            }
        })
    }
}