package com.example.party_game.core.device

import android.app.Activity
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoTracker
import androidx.window.layout.WindowLayoutInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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
                // No hinge feature:
                // If window is small, device is folded
                if (isLikelyFolded()) DeviceState.FOLDED
                else DeviceState.UNFOLDED
            }

            foldingFeature.state == FoldingFeature.State.HALF_OPENED ->
                DeviceState.FOLDED

            foldingFeature.state == FoldingFeature.State.FLAT ->
                DeviceState.UNFOLDED

            else -> DeviceState.UNFOLDED
        }
    }

    private fun WindowLayoutInfo.isLikelyFolded(): Boolean {
        // When folded, outer display is typically narrow
        val bounds = displayFeatures.firstOrNull()?.bounds
        return bounds == null
    }


}
