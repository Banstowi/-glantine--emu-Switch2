// SPDX-FileCopyrightText: 2024 yuzu Emulator Project
// SPDX-License-Identifier: GPL-2.0-or-later

package io.github.banstowi.erable.features.input

import android.content.Context
import android.os.Build
import android.os.CombinedVibration
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.view.InputDevice
import androidx.annotation.Keep
import androidx.annotation.RequiresApi
import io.github.banstowi.erable.ErableApplication

@Keep
@Suppress("DEPRECATION")
interface ErableVibrator {
    fun supportsVibration(): Boolean

    fun vibrate(intensity: Float)

    companion object {
        fun getControllerVibrator(device: InputDevice): ErableVibrator =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                ErableVibratorManager(device.vibratorManager)
            } else {
                ErableVibratorManagerCompat(device.vibrator)
            }

        fun getSystemVibrator(): ErableVibrator =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vibratorManager = ErableApplication.appContext
                    .getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                ErableVibratorManager(vibratorManager)
            } else {
                val vibrator = ErableApplication.appContext
                    .getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                ErableVibratorManagerCompat(vibrator)
            }

        fun getVibrationEffect(intensity: Float): VibrationEffect? {
            if (intensity > 0f) {
                return VibrationEffect.createOneShot(
                    50,
                    (255.0 * intensity).toInt().coerceIn(1, 255)
                )
            }
            return null
        }
    }
}

@RequiresApi(Build.VERSION_CODES.S)
class ErableVibratorManager(private val vibratorManager: VibratorManager) : ErableVibrator {
    override fun supportsVibration(): Boolean {
        return vibratorManager.vibratorIds.isNotEmpty()
    }

    override fun vibrate(intensity: Float) {
        val vibration = ErableVibrator.getVibrationEffect(intensity) ?: return
        vibratorManager.vibrate(CombinedVibration.createParallel(vibration))
    }
}

class ErableVibratorManagerCompat(private val vibrator: Vibrator) : ErableVibrator {
    override fun supportsVibration(): Boolean {
        return vibrator.hasVibrator()
    }

    override fun vibrate(intensity: Float) {
        val vibration = ErableVibrator.getVibrationEffect(intensity) ?: return
        vibrator.vibrate(vibration)
    }
}
