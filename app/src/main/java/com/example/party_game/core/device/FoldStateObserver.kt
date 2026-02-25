package com.example.party_game.core.device

import android.app.Activity
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoTracker
import androidx.window.layout.WindowLayoutInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.party_game.core.device.DeviceState

class FoldStateObserver(
    activity: Activity
) {

    private val windowInfoTracker = WindowInfoTracker.getOrCreate(activity)

    val deviceState: Flow<DeviceState> =
        windowInfoTracker.windowLayoutInfo(activity)
            .map { layoutInfo -> layoutInfo.toDeviceState() }

    private fun WindowLayoutInfo.toDeviceState(): DeviceState {

        val foldingFeature = displayFeatures
            .filterIsInstance<FoldingFeature>()
            .firstOrNull()

        return when {
            foldingFeature == null -> {
                // No hinge reported → device fully folded
                DeviceState.Folded
            }

            foldingFeature.state == FoldingFeature.State.HALF_OPENED -> {
                DeviceState.Folded
            }

            foldingFeature.state == FoldingFeature.State.FLAT -> {
                DeviceState.DualScreen
            }

            else -> DeviceState.Folded
        }
    }
}
