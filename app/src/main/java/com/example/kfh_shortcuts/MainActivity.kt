package com.example.kfh_shortcuts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kfh_shortcuts.composable.LoginScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kfh_shortcuts.composable.CatalogScreen
import com.example.kfh_shortcuts.composable.MainNavHost
import com.example.kfh_shortcuts.composable.MyNavHost

import com.example.kfh_shortcuts.ui.theme.KFHShortcutsTheme
import com.example.kfh_shortcuts.viewmodel.ProductViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KFHShortcutsTheme {
                MyNavHost()
//               MainNavHost()

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KFHShortcutsTheme {
        Greeting("Android")
    }
}


