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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.party_game.core.device.DeviceState
import com.example.party_game.core.device.FoldStateObserver
import com.example.party_game.core.device.OrientationManager
import com.example.party_game.game.GameViewModel
import com.example.party_game.ui.common.FoldRequiredScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val foldStateObserver = FoldStateObserver(this)
        val orientationManager = OrientationManager(this)

        setContent {
            PartyFoldTheme {
                val deviceState by foldStateObserver.deviceState
                    .collectAsState(initial = DeviceState.UNFOLDED)

                // 👇 Apply orientation as a SIDE EFFECT
                LaunchedEffect(Unit) {
                    orientationManager.apply(deviceState)
                }

                LaunchedEffect(deviceState) {
                    orientationManager.apply(deviceState)
                }


                AppContent(deviceState = deviceState)
            }
        }
    }
}



//@Composable
//fun App() {
//    PartyFoldTheme {
//        AppContent()
//    }
//}

@Composable
fun AppContent(
    deviceState: DeviceState,
    gameViewModel: GameViewModel = viewModel()
) {
    val gameState by gameViewModel.state.collectAsState()

    var screen by remember { mutableStateOf(Screen.OPERATOR) }

    when (deviceState) {
        DeviceState.FOLDED -> FoldRequiredScreen()
        DeviceState.UNFOLDED -> {
            when (screen) {
                Screen.OPERATOR -> OperatorScreen(
                    taskText = gameState.taskText,
                    onNext = gameViewModel::nextStep,
                    onSwitch = { screen = Screen.HELPERS }
                )
                Screen.HELPERS -> HelpersScreen(
                    helpersText = gameState.helpersText,
                    onSwitch = { screen = Screen.OPERATOR }
                )
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun AppUnfoldedPreview() {
    PartyFoldTheme {
        AppContent(deviceState = DeviceState.UNFOLDED)
    }
}

@Preview(showBackground = true)
@Composable
fun AppFoldedPreview() {
    PartyFoldTheme {
        AppContent(deviceState = DeviceState.FOLDED)
    }
}



