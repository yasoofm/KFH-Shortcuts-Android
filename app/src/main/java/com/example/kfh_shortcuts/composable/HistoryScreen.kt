package com.example.kfh_shortcuts.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp


@Composable
fun HistoryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8))
    ) {
        TopBaaaar()
    }
}
@Composable
fun TopBaaaar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
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
    }
}
