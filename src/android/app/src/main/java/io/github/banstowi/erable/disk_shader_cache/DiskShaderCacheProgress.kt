// SPDX-FileCopyrightText: 2023 yuzu Emulator Project
// SPDX-License-Identifier: GPL-2.0-or-later

package io.github.banstowi.erable.disk_shader_cache

import androidx.annotation.Keep
import androidx.lifecycle.ViewModelProvider
import io.github.banstowi.erable.NativeLibrary
import io.github.banstowi.erable.R
import io.github.banstowi.erable.activities.EmulationActivity
import io.github.banstowi.erable.model.EmulationViewModel
import io.github.banstowi.erable.utils.Log

@Keep
object DiskShaderCacheProgress {
    private lateinit var emulationViewModel: EmulationViewModel

    private fun prepareViewModel() {
        emulationViewModel =
            ViewModelProvider(
                NativeLibrary.sEmulationActivity.get() as EmulationActivity
            )[EmulationViewModel::class.java]
    }

    @JvmStatic
    fun loadProgress(stage: Int, progress: Int, max: Int) {
        val emulationActivity = NativeLibrary.sEmulationActivity.get()
        if (emulationActivity == null) {
            Log.error("[DiskShaderCacheProgress] EmulationActivity not present")
            return
        }

        emulationActivity.runOnUiThread {
            when (LoadCallbackStage.values()[stage]) {
                LoadCallbackStage.Prepare -> prepareViewModel()
                LoadCallbackStage.Build -> emulationViewModel.updateProgress(
                    emulationActivity.getString(R.string.building_shaders),
                    progress,
                    max
                )

                LoadCallbackStage.Complete -> {}
            }
        }
    }

    // Equivalent to VideoCore::LoadCallbackStage
    enum class LoadCallbackStage {
        Prepare, Build, Complete
    }
}
