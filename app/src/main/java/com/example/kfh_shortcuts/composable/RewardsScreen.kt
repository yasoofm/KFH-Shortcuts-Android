
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kfh_shortcuts.composable.CongratsDialog
import com.example.kfh_shortcuts.model.response.Reward
import com.example.kfh_shortcuts.viewmodel.ProductViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun RewardsScreen(viewModel: ProductViewModel = viewModel(), returnToCatalog: () -> Unit) {
    val showRedeemDialog = remember { mutableStateOf(false) }
    val showRewardDialog = remember { mutableStateOf(false) }
    val selectedRewardId = remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8))
    ) {
        TopBar(
            name = viewModel.token!!.firstName,
            lastName = viewModel.token!!.lastName,
            id = viewModel.token!!.kfH_Id,
            points = viewModel.earnedPoint!!.points
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Rewards",
            color = Color.Gray,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )

        RewardList(viewModel.rewards) { rewardId ->
            selectedRewardId.value = rewardId
            showRedeemDialog.value = true
        }

        if (showRedeemDialog.value) {
            RedeemDialog(
                onConfirm = {
                    showRedeemDialog.value = false
                    selectedRewardId.value?.let { viewModel.requestReward(it) }
                    showRewardDialog.value = true
                },
                onDismiss = { showRedeemDialog.value = false }
            )
        }

        if (showRewardDialog.value) {
            CongratsDialog(
                title = "Your request has \nbeen submitted",
                description = "The admin will contact you",
                titleSize = 30.sp,
                descriptionSize = 23.sp,
                onDismiss = {
                    showRewardDialog.value = false
                }
            )
        }
    }
}

@Composable
fun TopBar(name: String, lastName: String, id: Int, points: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .graphicsLayer {
                clip = true
                shape = RoundedCornerShape(bottomStart = 29.dp, bottomEnd = 29.dp)
            }
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF007A3D), Color(0xFF0D4228), Color(0xFF000000)),

                )
            )
            .padding(20.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Text(
            text = "My Box",
            color = Color.White,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.TopCenter)
        )

        Column(modifier = Modifier.align(Alignment.CenterStart)) {
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
fun RewardList(rewards: List<Reward>, onRewardClick: (Int) -> Unit) {
    LazyColumn {
        items(rewards) { reward ->
            RewardItem(
                title = reward.title,
                points = "${reward.requiredPoints} Points",
                dueDate = reward.dueDate
            ) { onRewardClick(reward.id) }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun RewardItem(title: String, points: String, dueDate: Date, onClick: () -> Unit) {
    val formattedDate = formatDueDate(dueDate)
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(horizontal = 16.dp)
            .clickable(onClick = onClick)
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = title,
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Due: $formattedDate",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Text(
                text = points,
                color = Color.Gray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

fun formatDueDate(dueDate: Date): String {
    return try {
        val dayYearFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        dayYearFormat.format(dueDate)
    } catch (e: Exception) {
        dueDate.toString()
    }
}

@Composable
fun RedeemDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(
                    text = "Yes",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = "No",
                    color = Color.Gray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        text = {
            Text(
                text = "Do you want to redeem this reward?",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        },
        shape = RoundedCornerShape(16.dp)
    )
}
