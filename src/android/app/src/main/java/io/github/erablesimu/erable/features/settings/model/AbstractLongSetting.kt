// SPDX-FileCopyrightText: 2023 yuzu Emulator Project
// SPDX-License-Identifier: GPL-2.0-or-later

package io.github.erablesimu.erable.features.settings.model

interface AbstractLongSetting : AbstractSetting {
    fun getLong(needsGlobal: Boolean = false): Long
    fun setLong(value: Long)
}
