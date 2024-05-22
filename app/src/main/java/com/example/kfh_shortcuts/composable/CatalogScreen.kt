package com.example.kfh_shortcuts.composable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun CatalogScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8))
            .padding(16.dp)
    ) {
        Header()
        Spacer(modifier = Modifier.height(16.dp))
        CardSection()
    }
}

@Composable
fun Header() {
    Column {
        Text(
            text = "Catalog",
            fontSize = 34.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CategoryChip("Cards", selected = true)
            CategoryChip("Financial investment", selected = false)
            CategoryChip("Financial investment", selected = false)
        }
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
            .padding(horizontal = 14.dp, vertical = 7.dp)
    ) {
        Text(
            text = text,
            color = if (selected) Color.White else Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun CardSection() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            CardItem("VISA", "Basic Card", Brush.linearGradient(listOf(Color.Blue, Color.Magenta, Color.Yellow)))
            CardItem("Mastercard", "Silver Card", Brush.linearGradient(listOf(Color.Gray, Color.White)))
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            CardItem("Mastercard", "Gold Card", Brush.linearGradient(listOf(Color.Yellow, Color.White)))
            CardItem("VISA", "Platinum Card", Brush.linearGradient(listOf(Color.Black, Color.DarkGray)))
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


