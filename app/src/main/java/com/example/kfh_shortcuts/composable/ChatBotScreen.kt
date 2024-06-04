import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.SettingsInputAntenna
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kfh_shortcuts.R
import com.example.kfh_shortcuts.model.ChatbotRequest
import com.example.kfh_shortcuts.model.response.ChatbotResponse
import com.example.kfh_shortcuts.viewmodel.ProductViewModel

@Composable
fun ChatBotScreen(viewModel: ProductViewModel) {
    var text = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8))
    ) {
        TopBar(
            name = viewModel.token!!.firstName,
            lastName = viewModel.token!!.lastName,
            id = viewModel.token!!.kfH_Id,        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(viewModel.messages) { message ->
                if (message.role == "user") {
                    MessageBubbleUser(message.message)
                } else {
                    MessageBubbleBot(message.message)
                }
            }
        }
        ScrollableRow(items = listOf("Cards", "Financial investment", "Prepaid", "Cards"))
        BottomInputField(
            text = text,
            onTextChange = { text.value = it },
            viewModel = viewModel

        )
    }
}

@Composable
fun TopBar(name: String,lastName: String, id: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .graphicsLayer {
                clip = true
                shape = RoundedCornerShape(bottomStart = 29.dp, bottomEnd = 29.dp)
            }
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF007A3D), Color(0xFF0D4228), Color(0xFF000000))
                )
            )
            .padding(20.dp),
        contentAlignment = Alignment.TopStart
    ) {
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
    }
}

@Composable
fun ScrollableRow(items: List<String>) {
    LazyRow(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) { item ->
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color(0xFF141414),
                        shape = RoundedCornerShape(size = 100.dp)
                    )
                    .padding(start = 14.dp, top = 7.dp, end = 14.dp, bottom = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item,
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun BottomInputField(
    viewModel: ProductViewModel,
    text: MutableState<String>,
    onTextChange: (String) -> Unit,

    ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = text.value,
            onValueChange = onTextChange,
            modifier = Modifier
                .weight(1f)
                .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
            placeholder = {
                Text(text = "Type a message...")
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {
                val newList = ArrayList(viewModel.messages)
                newList.add(ChatbotResponse(text.value, "user"))
                viewModel.messages = newList
                viewModel.chat(request = ChatbotRequest(text.value))
                text.value = ""
                      },
            modifier = Modifier
                .size(35.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(Icons.Default.ArrowUpward, contentDescription = "send")
        }
    }
}

@Composable
fun MessageBubbleUser(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 50.dp)
            .wrapContentWidth(Alignment.End)
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFF4CB380), shape = RoundedCornerShape(8.dp))
                .padding(8.dp)
        ) {
            Text(
                text = message,
                color = Color.Black,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun MessageBubbleBot(message: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 50.dp)
            .wrapContentWidth(Alignment.Start),
        verticalAlignment = Alignment.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.rectangle),
            contentDescription = "Bot Icon",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(24.dp)

        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = "KFH Ambassador",
                style = TextStyle(
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF000E08),
                )
            )
            Box(
                modifier = Modifier
                    .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                    .padding(8.dp)
            ) {
                Text(
                    text = message,
                    color = Color.Black,
                    fontSize = 16.sp

                )
            }
        }
    }
}



