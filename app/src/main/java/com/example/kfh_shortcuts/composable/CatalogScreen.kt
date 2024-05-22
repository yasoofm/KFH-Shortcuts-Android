

package com.example.kfh_shortcuts.composable



import android.annotation.SuppressLint

import androidx.compose.foundation.background

import androidx.compose.foundation.border

import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Face

import androidx.compose.material.icons.filled.Home

import androidx.compose.material.icons.filled.List

import androidx.compose.material.icons.filled.Star

import androidx.compose.material3.*

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier

import androidx.compose.ui.geometry.Offset

import androidx.compose.ui.graphics.Brush

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.graphics.graphicsLayer

import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp

import androidx.compose.ui.unit.sp



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable

fun CatalogScreen() {

    Scaffold(

        floatingActionButton = {

            FloatingActionButton(

                onClick = { /* TODO: Add action */ },

                shape = CircleShape,

                containerColor = Color(0xFF007A3D),

                contentColor = Color.White,

                modifier = Modifier.padding(bottom = 80.dp, end = 16.dp) // Adjust bottom padding to avoid overlapping with bottom bar

            ) {

                Icon(Icons.Default.Face, contentDescription = "Chat")

            }

        },

        floatingActionButtonPosition = FabPosition.End,

        bottomBar = {

            BottomAppBar(

                modifier = Modifier

                    .fillMaxWidth()

                    .height(80.dp)

            ) {

                BottomNavBar()

            }

        },

        topBar = {

            TopBar()

        }

    ) {

        Column(

            modifier = Modifier

                .fillMaxSize()

                .background(Color(0xFFF8F8F8))

                .padding(it.calculateTopPadding())

        ) {

            CardSection()

        }

    }

}



@Composable

fun TopBar() {

    Box(

        modifier = Modifier

            .fillMaxWidth()

            .height(250.dp)

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

        contentAlignment = Alignment.TopStart

    ) {

        Column(

            modifier = Modifier

                .fillMaxSize()

                .padding(horizontal = 16.dp, vertical = 24.dp),

            verticalArrangement = Arrangement.SpaceBetween,

            horizontalAlignment = Alignment.Start

        ) {

            Spacer(modifier = Modifier.height(80.dp))

            Text(

                text = "Catalog",

                fontSize = 34.sp,

                lineHeight = 40.sp,

                fontWeight = FontWeight.Medium,

                color = Color.White,

                modifier = Modifier.padding(bottom = 8.dp)

            )

            Spacer(modifier = Modifier.weight(1f))


            Row(

                modifier = Modifier

                    .fillMaxWidth()

                    .padding(bottom = 16.dp),

                horizontalArrangement = Arrangement.SpaceEvenly

            ) {

                CategoryChip("Cards", selected = true)

                CategoryChip("Investments", selected = false)

                CategoryChip("Loans", selected = false)

            }

        }

    }

}



@Composable

fun BottomNavBar() {

    Row(

        modifier = Modifier

            .fillMaxWidth()

            .background(

                Brush.linearGradient(

                    colors = listOf(

                        Color(0xFF0D4228),

                        Color(0xFF007A3D),

                        Color.Black

                    )

                )

            )

            .padding(vertical = 10.dp),

        horizontalArrangement = Arrangement.SpaceEvenly,

        verticalAlignment = Alignment.CenterVertically

    ) {

        NavItem(Icons.Default.Home, "Catalog")

        NavItem(Icons.Default.List, "History")

        NavItem(Icons.Default.Star, "Rewards")

    }

}



@Composable

fun NavItem(icon: ImageVector, label: String) {

    Column(

        horizontalAlignment = Alignment.CenterHorizontally,

        modifier = Modifier.clickable { /* TODO: Add action */ }

    ) {

        Icon(

            imageVector = icon,

            contentDescription = label,

            tint = Color.White,

            modifier = Modifier.size(24.dp)

        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(

            text = label,

            color = Color.White,

            fontSize = 12.sp

        )

    }

}



@Composable

fun CategoryChip(text: String, selected: Boolean) {

    Box(

        modifier = Modifier

            .background(

                color = if (selected) Color(0xFF0D4228) else Color.Transparent,

                shape = RoundedCornerShape(100.dp)

            )

            .border(

                width = 1.dp,

                color = if (selected) Color.Transparent else Color.White,

                shape = RoundedCornerShape(100.dp)

            )

            .padding(horizontal = 16.dp, vertical = 8.dp)

    ) {

        Text(

            text = text,

            color = if (selected) Color.White else Color.White,

            fontSize = 16.sp,

            fontWeight = FontWeight.Medium

        )

    }

}



@Composable

fun CardSection() {

    Column(

        verticalArrangement = Arrangement.spacedBy(16.dp),

        horizontalAlignment = Alignment.CenterHorizontally,

        modifier = Modifier

            .fillMaxWidth()

            .padding(16.dp)

    ) {

        Row(

            horizontalArrangement = Arrangement.spacedBy(16.dp),

            modifier = Modifier.fillMaxWidth()

        ) {

            CardItem(

                type = "VISA",

                description = "Basic Card",

                background = Brush.linearGradient(listOf(Color.Blue, Color.Magenta, Color.Yellow))

            )

            CardItem(

                type = "Mastercard",

                description = "Silver Card",

                background = Brush.linearGradient(listOf(Color.Gray, Color.White))

            )

        }

        Row(

            horizontalArrangement = Arrangement.spacedBy(16.dp),

            modifier = Modifier.fillMaxWidth()

        ) {

            CardItem(

                type = "Mastercard",

                description = "Gold Card",

                background = Brush.linearGradient(listOf(Color.Yellow, Color.White))

            )

            CardItem(

                type = "VISA",

                description = "Platinum Card",

                background = Brush.linearGradient(listOf(Color.Black, Color.DarkGray))

            )

        }

    }

}



@Composable

fun CardItem(type: String, description: String, background: Brush) {

    Box(

        modifier = Modifier

            .size(150.dp)

            .background(background, RoundedCornerShape(10.dp))

            .padding(16.dp)

    ) {

        Column {

            Text(

                text = type,

                fontSize = 20.sp,

                fontWeight = FontWeight.Bold,

                color = Color.White

            )

            Spacer(modifier = Modifier.weight(1f))

            Text(

                text = description,

                fontSize = 16.sp,

                fontWeight = FontWeight.Medium,

                color = Color.White

            )

        }

    }

}