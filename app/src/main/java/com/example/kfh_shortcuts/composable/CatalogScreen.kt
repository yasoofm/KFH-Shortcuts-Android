package com.example.kfh_shortcuts.composable

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.kfh_shortcuts.model.Categorey
import com.example.kfh_shortcuts.model.Product
import com.example.kfh_shortcuts.viewmodel.ProductViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CatalogScreen(viewModel: ProductViewModel = viewModel(), openProductDetails: () -> Unit) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8))
    ) {
        TopBar(
            viewModel.categories,
            viewModel.selectedCategoryName,
            viewModel::fetchProductsByCategory
        )
        Spacer(modifier = Modifier.height(16.dp))
        ProductSection(viewModel.productItems)
    }
}

@Composable
fun TopBar(
    categories: List<Categorey>,
    selectedCategoryName: String?,
    onCategorySelected: (String) -> Unit
) {
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
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { category ->
                    CategoryChip(
                        text = category.name,
                        selected = category.name == selectedCategoryName,
                        onClick = {

                            onCategorySelected(category.name)

                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryChip(text: String, selected: Boolean, onClick: () -> Unit) {
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
            .clickable(onClick = onClick)
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
fun ProductSection(productItems: List<Product>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        productItems.chunked(2).forEach { rowItems ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                rowItems.forEach { item ->
                    ProductItem(
                        name = item.name,
                        description = item.description,
                        imageUrl = item.image
                    )
                }
            }
        }
    }
}

@Composable
fun ProductItem(name: String, description: String, imageUrl: String) {
    Box(
        modifier = Modifier
            .width(169.461.dp)
            .height(116.975.dp)
            .background(Color(0xFFF8F8F8), RoundedCornerShape(8.812.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.812.dp))
            .padding(16.dp)
    ) {
        Column {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = description,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
        }
    }
}
