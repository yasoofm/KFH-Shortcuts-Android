package com.example.kfh_shortcuts.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.kfh_shortcuts.R
import com.example.kfh_shortcuts.model.Categorey
import com.example.kfh_shortcuts.viewmodel.ProductViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CatalogScreen(
    viewModel: ProductViewModel = viewModel(),
    openProductDetails: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        TopBar(
            categories = viewModel.categories,
            onCategorySelected = viewModel::fetchProductsByCategory,
            viewModel
        )

        ProductSection(viewModel, openProductDetails)
    }
}

@Composable
fun TopBar(
    categories: List<Categorey>,
    onCategorySelected: (String) -> Unit,
    viewModel: ProductViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .graphicsLayer {
                clip = true
                shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
            }
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF007A3D),
                        Color(0xFF0D4228),
                        Color(0xFF000000)
                    ),
                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            ),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 0.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_kfh_shortcuts),
                contentDescription = "Logo",
                Modifier
                    .size(100.dp)
                    .padding(top = 20.dp)
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(categories) { index, category ->
                    if (index == 0) {
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                    CategoryChip(
                        text = category.name,
                        selected = category.name == viewModel.selectedCategoryName,
                        onClick = { onCategorySelected(category.name) }
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
                color = if (selected) Color.White else Color.Transparent,
                shape = RoundedCornerShape(50.dp)
            )
            .border(
                width = 1.dp,
                color = if (selected) Color.Transparent else Color.White,
                shape = RoundedCornerShape(50.dp)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            color = if (selected) Color(0xFF0D4228) else Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun ProductSection(
    viewModel: ProductViewModel = viewModel(),
    openProductDetails: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        itemsIndexed(viewModel.productItems.chunked(2)) { _, rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                rowItems.forEach { item ->
                    ProductItem(
                        name = item.name,
                        description = item.description,
                        imageUrl = item.image,
                        onClick = {
                            viewModel.selectedProduct = item
                            openProductDetails(item.id.toString())
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun ProductItem(
    name: String,
    description: String,
    imageUrl: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
                .shadow(4.dp, RoundedCornerShape(8.dp))
                .background(color = Color(0xFFEBE4E4), RoundedCornerShape(8.dp))
                .padding(10.dp)
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF0D4228)
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.TopCenter)
                .offset(y = (-60).dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Fit
        )
    }
}


