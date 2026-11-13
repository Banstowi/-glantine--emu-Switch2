# SPDX-FileCopyrightText: Copyright 2026 Eden Emulator Project
# SPDX-License-Identifier: GPL-3.0-or-later

# SPDX-FileCopyrightText: 2017 Citra Emulator Project
# SPDX-License-Identifier: GPL-2.0-or-later

add_library(erable-room STATIC EXCLUDE_FROM_ALL
    erable_room.cpp
    erable_room.h
    erable_room.rc)

target_link_libraries(erable-room PRIVATE common network)
if (ENABLE_WEB_SERVICE)
    target_compile_definitions(erable-room PRIVATE ENABLE_WEB_SERVICE)
    target_link_libraries(erable-room PRIVATE web_service)
endif()

target_link_libraries(erable-room PRIVATE
    OpenSSL::SSL
    OpenSSL::Crypto)

if (MSVC)
    target_link_libraries(erable-room PRIVATE getopt)
endif()
target_link_libraries(erable-room PRIVATE ${PLATFORM_LIBRARIES} Threads::Threads)

create_target_directory_groups(erable-room)
