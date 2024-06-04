import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kfh_shortcuts.model.RequestHistory
import com.example.kfh_shortcuts.viewmodel.ProductViewModel


@Composable
fun HistoryScreen(viewModel: ProductViewModel = viewModel()) {


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopBarH(
            name = viewModel.token!!.firstName,
            lastName = viewModel.token!!.lastName,
            id = viewModel.token!!.kfH_Id,
            points = viewModel.earnedPoint!!.points
        )
        HistoryList(viewModel.history)
    }
}

@Composable
fun TopBarH(name: String, lastName: String, id: Int, points: Int) {
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
                    colors = listOf(Color(0xFF007A3D), Color(0xFF0D4228), Color(0xFF000000)),
                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            )
            .padding(20.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Text(
            text = "History",
            color = Color.White,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.TopEnd)
        )
        Column(modifier = Modifier.align(Alignment.TopStart)) {
            Text(
                text = "Welcome 👋",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$name $lastName",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = id.toString(),
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(
                text = "Total Points",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = points.toString(),
                color = Color.White,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun HistoryList(history: List<RequestHistory>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(history) { item ->
            HistoryItem(productName = item.productName, points = item.points)
        }
    }
}

@Composable
fun HistoryItem(productName: String, points: Int) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.History,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = productName,
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Text(
                text = points.toString(),
                color = Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


