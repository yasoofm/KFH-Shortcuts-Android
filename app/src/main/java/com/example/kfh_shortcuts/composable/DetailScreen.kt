package com.example.kfh_shortcuts.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.kfh_shortcuts.R

@Composable
fun DetailScreen(viewModel: ViewModel, openRequestDetails: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8))
    ) {
        TopBaar()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8F8F8))
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
//            CardItem(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                type = "mastercard",
//                description = "",
//                background = Brush.linearGradient(listOf(Color.Gray, Color.White))
//
//            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Details about the card",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(text = "Description", fontSize = 18.sp, color = Color.Black)
                Text(text = "shariah ", fontSize = 18.sp, color = Color.Black)
            }
            Spacer(modifier = Modifier.height(16.dp))


            Button(

                onClick = {
                    openRequestDetails()
                },

                Modifier
                    .width(344.dp)
                    .height(63.dp)
                    .background(color = Color(0xFF007A3D), shape = RoundedCornerShape(size = 8.dp))
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp),

                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007A3D))
            )
            {

                Text("Request", fontSize = 16.sp, color = Color.White)

            }




        }

    }
}
@Composable
fun TopBaar() {
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

