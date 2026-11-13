# SPDX-FileCopyrightText: Copyright 2026 Eden Emulator Project
# SPDX-License-Identifier: GPL-3.0-or-later

# The build options were renamed from YUZU_<name> to ERABLE_<name> (docs/nx2/rename.md).
# A configure that still passes or caches the old name carries its value to the new one,
# so existing build directories and scripts keep their settings. A configure cannot tell an
# explicit -DERABLE_<name> from its cache entry, so the old name keeps deciding while
# ERABLE_<name> still holds the value last carried from it, and warns when that replaces a
# different value. A ERABLE_<name> set to anything else wins and the old name is ignored.
set(_erable_renamed_options
    BUILD_PRESET CMD CRASH_DUMPS DISABLE_LLVM DOWNLOAD_ANDROID_VVL DOWNLOAD_TIME_ZONE_DATA
    INSTALL_UDEV_RULES LEGACY QT_LANGUAGES QT_MIRROR ROOM ROOM_STANDALONE STATIC_BUILD STATIC_ROOM
    TESTS TZDB_PATH USE_BUNDLED_FFMPEG USE_BUNDLED_MOLTENVK USE_BUNDLED_OPENSSL USE_BUNDLED_QT
    USE_BUNDLED_SDL3 USE_BUNDLED_SIRIT USE_EXTERNAL_FFMPEG USE_QT_MULTIMEDIA USE_QT_WEB_ENGINE)

foreach (_name IN LISTS _erable_renamed_options)
    if (NOT DEFINED CACHE{YUZU_${_name}})
        continue()
    endif()
    set(_old "${YUZU_${_name}}")
    # The new name was carried from the old one before (or never set), so the old name still decides.
    if (NOT DEFINED ERABLE_${_name} OR "${ERABLE_${_name}}" STREQUAL "${_ERABLE_CARRIED_${_name}}")
        if (DEFINED ERABLE_${_name} AND NOT "${ERABLE_${_name}}" STREQUAL "${_old}")
            # A configure cannot tell a -DERABLE_<name> repeating the carried value from the cached carry.
            message(WARNING "YUZU_${_name}=${_old} replaces ERABLE_${_name}=${ERABLE_${_name}}; pass only ERABLE_${_name}")
        endif()
        message(DEPRECATION "YUZU_${_name} is now ERABLE_${_name}; using YUZU_${_name}=${_old}")
        set(ERABLE_${_name} "${_old}" CACHE STRING "" FORCE)
        set(_ERABLE_CARRIED_${_name} "${_old}" CACHE INTERNAL "")
    else()
        message(WARNING "YUZU_${_name}=${_old} is ignored: ERABLE_${_name}=${ERABLE_${_name}} is set")
    endif()
    unset(YUZU_${_name} CACHE)
endforeach()
