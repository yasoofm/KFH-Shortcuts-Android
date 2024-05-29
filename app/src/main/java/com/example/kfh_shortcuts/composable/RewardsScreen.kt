import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
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

@Composable
fun RewardsScreen(viewModel: ProductViewModel = viewModel(), returnToCatalog: () -> Unit) {
    val showRedeemDialog = remember { mutableStateOf(false) }
    val showRewardDialog = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8))
    ) {
        TopBaaaar(name = viewModel.token!!.firstName, lastName = viewModel.token!!.lastName, id = viewModel.token!!.kfH_Id, points = "1000")
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Rewards",
            color = Color.Gray,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )

        RewardList(viewModel.rewards) { showRedeemDialog.value = true }

        if (showRedeemDialog.value) {
            RedeemDialog(
                onConfirm = {
                    showRedeemDialog.value = false
                    showRewardDialog.value = true
                            },
                onDismiss = { showRedeemDialog.value = false }
            )
        }

        if (showRewardDialog.value)
        {
            CongratsDialog( title = "Your request has \n" + "been submitted",
                description = "The admin will contact you", titleSize = 30.sp, descriptionSize = 23.sp,
                onDismiss = {
                    showRewardDialog.value = false
                })
        }
    }
}
@Composable
fun TopBaaaar(name: String,lastName:String, id: Int, points: String) {
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
                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            )
            .padding(20.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Spacer(modifier = Modifier.height(600.dp))
                Text(
                    text = "My box",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            Spacer(modifier = Modifier.height(9.dp))
            Column(modifier = Modifier.align(Alignment.CenterStart)) {
                Text(
                    text = "Welcome 👋",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = name,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = lastName,
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
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Text(
                    text = "total points",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = points,
                    color = Color.White,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold
                )

            }
        }
    }
@Composable
fun RewardList(rewards: List<Reward>, onRewardClick: () -> Unit) {
    LazyColumn {
      items(rewards) { reward ->
           RewardItem(
           title = reward.title,
           points = "${reward.requiredPoints} Points",
               onClick = onRewardClick)
            Spacer(modifier = Modifier.height(25.dp))
        }
    }
}

@Composable
fun RewardItem(title: String, points: String, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 16.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = points,
                color = Color.Gray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun RedeemDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Yes",
                    color = Color.Black,
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
