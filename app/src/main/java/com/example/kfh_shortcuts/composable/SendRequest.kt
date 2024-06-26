package com.example.kfh_shortcuts.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kfh_shortcuts.R
import com.example.kfh_shortcuts.model.Product
import com.example.kfh_shortcuts.viewmodel.ProductViewModel

@Composable
fun SendRequest(viewModel: ProductViewModel, returnToCatalog: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    var clientName by remember { mutableStateOf("") }
    var clientNumber by remember { mutableStateOf("") }
    var showNameAlert by remember { mutableStateOf(false) }
    var showNumberAlert by remember { mutableStateOf(false) }
    var awardedPoint = viewModel.selectedProduct?.awardedPoints


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8))
    ) {
        TopBaaar()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 9.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Send Your request",
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF141414),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(120.dp))
            Text(
                text = "Client's Name",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp)
            )
            OutlinedTextField(
                value = clientName,
                onValueChange = {
                    clientName = it
                    showNameAlert = clientName.isEmpty()
                },
                label = { Text("Enter Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            if (showNameAlert) {
                Text(
                    text = "Client's Name cannot be empty",
                    color = Color.Red,
                    style = TextStyle(fontSize = 12.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Client's Mobile Number",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp)
            )
            OutlinedTextField(
                value = clientNumber,
                onValueChange = {
                    clientNumber = it
                    showNumberAlert = clientNumber.isEmpty()
                },
                leadingIcon = { Text("+965") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            if (showNumberAlert) {
                Text(
                    text = "Client's Mobile Number cannot be empty",
                    color = Color.Red,
                    style = TextStyle(fontSize = 12.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    showNameAlert = clientName.isEmpty()
                    showNumberAlert = clientNumber.isEmpty()
                    if (!showNameAlert && !showNumberAlert) {
                        viewModel.productRequest(clientName, clientNumber)
                        showDialog = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .offset(y = (-10).dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF007A3D),
                    Color(0xFF0D4228),
                    Color(0xFF000000)
                )
            ) {
                Text("Request", fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(32.dp))

            if (showDialog) {
                CongratsDialog(
                    description = "You earned $awardedPoint Points",
                    onDismiss = {
                    showDialog = false
                    returnToCatalog()
                })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CongratsDialog(
    onDismiss: () -> Unit,
    title: String = "Congrats!",
    description: String = "You earned 10 points",
    titleSize: TextUnit = 40.sp,
    descriptionSize: TextUnit = 27.sp
) {
    AlertDialog(
        modifier = Modifier
            .width(368.dp)
            .height(211.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF0E7A12), Color(0xFF1D975B), Color(0xFF6FE273)),
                )
            )
            .clickable {
                onDismiss()
            },
        onDismissRequest = onDismiss
    ) {
        Column {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = titleSize,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(36.dp)
            )
            Text(
                text = description,
                style = TextStyle(
                    fontSize = descriptionSize,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun TopBaaar() {
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
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.vector),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(48.dp)
        )
    }
}
