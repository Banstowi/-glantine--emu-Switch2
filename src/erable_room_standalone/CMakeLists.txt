# SPDX-FileCopyrightText: Copyright 2026 Eden Emulator Project
# SPDX-License-Identifier: GPL-3.0-or-later

add_executable(erable_room_standalone erable_room_standalone.cpp)

set_target_properties(erable_room_standalone PROPERTIES OUTPUT_NAME "eden-room")

target_link_libraries(erable_room_standalone PRIVATE erable-room)

if(UNIX AND NOT APPLE)
    install(TARGETS erable_room_standalone RUNTIME DESTINATION "${CMAKE_INSTALL_PREFIX}/bin")
endif()

if (ERABLE_STATIC_ROOM)
    target_link_options(erable_room_standalone PRIVATE "-static")
endif()

create_target_directory_groups(erable_room_standalone)
