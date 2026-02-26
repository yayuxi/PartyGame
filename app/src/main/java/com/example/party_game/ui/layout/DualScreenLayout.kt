package com.example.party_game.ui.layout


import android.annotation.SuppressLint
import android.graphics.Rect
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoTracker
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun DualScreenLayout(
    operatorContent: @Composable () -> Unit,
    helperContent: @Composable () -> Unit
) {
    Row(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier.weight(1f)
        ) {
            operatorContent()
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .pointerInput(Unit) { } // blocks interaction
        ) {
            helperContent()
        }
    }
}