package com.example.party_game.core.device

import android.graphics.Rect

sealed class DeviceState {

    object Folded : DeviceState()

    object SingleScreen : DeviceState()

    data class DualScreen(
        val hingeBounds: Rect,
        val isVertical: Boolean
    ) : DeviceState()
}