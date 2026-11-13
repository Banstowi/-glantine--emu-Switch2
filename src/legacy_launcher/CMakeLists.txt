# SPDX-FileCopyrightText: Copyright 2026 Erable
# SPDX-License-Identifier: GPL-3.0-or-later

# banstdog.exe, the former name: starts erable.exe for older versions' updates and shortcuts.
add_executable(erable-legacy-launcher WIN32 main.cpp)
set_target_properties(erable-legacy-launcher PROPERTIES OUTPUT_NAME "banstdog")
target_compile_definitions(erable-legacy-launcher PRIVATE UNICODE _UNICODE)
target_link_libraries(erable-legacy-launcher PRIVATE user32)
if (MSVC)
    set_property(TARGET erable-legacy-launcher PROPERTY MSVC_RUNTIME_LIBRARY
                 "MultiThreaded$<$<CONFIG:Debug>:Debug>")
else()
    target_link_options(erable-legacy-launcher PRIVATE -static -municode)
endif()
