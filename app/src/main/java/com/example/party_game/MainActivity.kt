package com.example.party_game

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Surface
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import com.example.party_game.core.device.DeviceState
import com.example.party_game.core.device.FoldStateObserver
import com.example.party_game.game.GameViewModel
import com.example.party_game.ui.common.FoldRequiredScreen

class MainActivity : ComponentActivity() {

    private lateinit var foldStateObserver: FoldStateObserver

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.d("ORIENTATION_DEBUG", "onConfigurationChanged: ${newConfig.orientation}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        foldStateObserver = FoldStateObserver(this)

        lifecycleScope.launchWhenStarted {
            foldStateObserver.deviceState.collect { deviceState ->
                Log.d("ORIENTATION_DEBUG", "onCreate: $deviceState")
            }
        }

        setContent {
            PartyFoldTheme {
                val deviceState by foldStateObserver.deviceState
                    .collectAsState(initial = DeviceState.UNFOLDED)

                if (deviceState == DeviceState.FOLDED) {
                    AppContent(deviceState = deviceState)
                } else {
                    ReverseLandscapeContainer {
                        AppContent(deviceState = deviceState)
                    }
                }
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


@Composable
fun ReverseLandscapeContainer(
    content: @Composable () -> Unit
) {
    val configuration = LocalConfiguration.current

    val displayRotation = (LocalContext.current.getSystemService(Context.WINDOW_SERVICE) as WindowManager)
        .defaultDisplay
        .rotation // Returns Surface.ROTATION_0, _90, _180, _270

    val rotationDegrees = when (displayRotation) {
        Surface.ROTATION_0 -> 0f
        Surface.ROTATION_90 -> 90f
        Surface.ROTATION_180 -> 180f
        Surface.ROTATION_270 -> 270f
        else -> 0f
    }

    val finalRotation = (270f - rotationDegrees + 360f) % 360f

    Box(
        modifier = Modifier
            .fillMaxSize()
            .rotate(finalRotation)
    ) {
        content()
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



