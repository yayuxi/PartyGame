package com.example.party_game.core.device

import android.app.Activity
import android.content.pm.ActivityInfo

class OrientationManager(
    private val activity: Activity
) {

    fun apply(deviceState: DeviceState) {
        activity.requestedOrientation =
            when (deviceState) {
                DeviceState.UNFOLDED ->
                    ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE
                DeviceState.FOLDED ->
                    ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            }
    }
}
