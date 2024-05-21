
@file:OptIn(ExperimentalMaterial3Api::class)



package com.example.kfh_shortcuts.composable



import androidx.compose.foundation.Image

import androidx.compose.foundation.background

import androidx.compose.foundation.border

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.*

import androidx.compose.runtime.*

import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Brush

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.graphics.graphicsLayer

import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.input.PasswordVisualTransformation

import androidx.compose.ui.text.input.VisualTransformation

import androidx.compose.ui.unit.dp

import androidx.compose.ui.unit.sp

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import com.example.kfh_shortcuts.R



@Composable

fun LoginScreen() {

    Column(

        modifier = Modifier.fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Box(

            modifier = Modifier

                .fillMaxWidth()

                .height(358.dp)

                .graphicsLayer {

                    clip = true

                    shape = RoundedCornerShape(bottomStart = 29.dp, bottomEnd = 29.dp)

                }

                .background(

                    brush = Brush.linearGradient(

                        colors = listOf(Color(0xFF007A3D), Color(0xFF0D4228), Color(0xFF000000)),

                        start = Offset.Zero,

                        end = Offset.Infinite

                    )

                ),

            contentAlignment = Alignment.Center

        ) {

            Column(

                horizontalAlignment = Alignment.CenterHorizontally,

                verticalArrangement = Arrangement.Center

            ) {

                Logo()

                Spacer(modifier = Modifier.height(16.dp))

                Title()

            }

        }

        Spacer(modifier = Modifier.height(32.dp))

        Column(

            modifier = Modifier

                .padding(16.dp)

                .fillMaxWidth(),

            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            SignInPrompt()

            Spacer(modifier = Modifier.height(32.dp))

            EmailField()

            Spacer(modifier = Modifier.height(16.dp))

            PasswordField()

            Spacer(modifier = Modifier.height(32.dp))

            LoginButton()

        }

    }

}



@Composable

fun Logo() {

    Image(

        painter = painterResource(id = R.drawable.kfh_logo),

        contentDescription = "KFH Logo",

        modifier = Modifier.size(100.dp)

    )

}



@Composable

fun Title() {

    Text(
        modifier = Modifier
            .width(131.dp)
            .height(35.dp),
        text = "KFH \n",
        style = TextStyle(
            fontSize = 34.sp,
            lineHeight = 22.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFFFFFFFF),
            textAlign = TextAlign.Center,

            )


    )

    Text(
        text = "Shortcuts\n",
        modifier = Modifier
            .width(218.dp)
            .height(35.dp),

        style = TextStyle(
            fontSize = 34.sp,
            lineHeight = 22.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFFFFFFFF),
            textAlign = TextAlign.Center,
        )
    )

}



@Composable

fun SignInPrompt() {

    Text("Let's sign you in.", style = MaterialTheme.typography.headlineMedium, color = Color.Black)

}



@OptIn(ExperimentalMaterial3Api::class)

@Composable

fun EmailField() {

    var email by remember { mutableStateOf("") }



    OutlinedTextField(

        value = email,

        onValueChange = { email = it },

        label = { Text("Email") },

        modifier = Modifier

            .fillMaxWidth()

            .background(Color(0xFFF6F6F6))

            .border(1.dp, Color.White, shape = RoundedCornerShape(8.dp)),

        singleLine = true,

        colors = TextFieldDefaults.outlinedTextFieldColors(

            unfocusedBorderColor = Color.Transparent,

            focusedBorderColor = Color.White,

            cursorColor = Color.Black,

            focusedLabelColor = Color.Black,

            unfocusedLabelColor = Color.Gray,

            containerColor = Color(0xFFF6F6F6),

            )

    )

}



@Composable

fun PasswordField() {

    var password by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }



    OutlinedTextField(

        value = password,

        onValueChange = { password = it },

        label = { Text("Password") },

        modifier = Modifier

            .fillMaxWidth()

            .background(Color(0xFFF6F6F6))

            .border(1.dp, Color.White, shape = RoundedCornerShape(8.dp)),

        singleLine = true,

        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),

        trailingIcon = {

            val image = if (passwordVisible)

                painterResource(id = R.drawable.ic_visible)

            else painterResource(id = R.drawable.ic_visible)



            IconButton(onClick = { passwordVisible = !passwordVisible }) {

                Image(painter = image, contentDescription = null)

            }

        },

        colors = TextFieldDefaults.outlinedTextFieldColors(

            unfocusedBorderColor = Color.Transparent,

            focusedBorderColor = Color.White,

            cursorColor = Color.Black,

            focusedLabelColor = Color.Black,

            unfocusedLabelColor = Color.Gray,

            containerColor = Color(0xFFF6F6F6),

            )

    )

}



@Composable

fun LoginButton() {

    Button(

        onClick = { /* Handle login */ },

        modifier = Modifier

            .fillMaxWidth()

            .height(50.dp)

            .background(Color(0xFF007A3D)),

        shape = RoundedCornerShape(8.dp),

        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007A3D))

    ) {

        Text("Login", fontSize = 16.sp, color = Color.White)

    }

}



