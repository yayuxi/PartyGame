package com.example.party_game.ui.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun FoldRequiredScreen() {
    ScreenScaffold(title = "Device Closed") {
        Text("Please unfold your device to play 🎉")
    }
}
