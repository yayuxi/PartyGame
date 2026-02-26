package com.example.party_game.ui.common

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import android.view.WindowManager
import android.view.Surface
import androidx.compose.ui.platform.LocalView

@Composable
fun ForcedRotationContainer(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    val rotation = view.display?.rotation ?: 0

    val deviceRotationDegrees = when (rotation) {
        Surface.ROTATION_0 -> 0f
        Surface.ROTATION_90 -> 90f
        Surface.ROTATION_180 -> 180f
        Surface.ROTATION_270 -> 270f
        else -> 0f
    }

    // Lock visually to 270° relative to portrait
    val finalRotation = (270f - deviceRotationDegrees + 360f) % 360f

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.graphicsLayer {
                rotationZ = finalRotation
                transformOrigin = TransformOrigin(0.5f, 0.5f)
            }
        ) {
            content()
        }
    }
}