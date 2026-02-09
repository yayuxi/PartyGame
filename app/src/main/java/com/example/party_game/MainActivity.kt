package com.example.party_game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import com.example.party_game.ui.helpers.HelpersScreen
import com.example.party_game.ui.operator.OperatorScreen
import com.example.party_game.ui.theme.PartyFoldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    PartyFoldTheme {
        AppContent()
    }
}


@Composable
fun AppContent() {
    var screen by remember { mutableStateOf(Screen.OPERATOR) }

    when (screen) {
        Screen.OPERATOR -> OperatorScreen(
            onSwitch = { screen = Screen.HELPERS }
        )
        Screen.HELPERS -> HelpersScreen(
            onSwitch = { screen = Screen.OPERATOR }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AppPreview() {
    PartyFoldTheme {
        AppContent()
    }
}

