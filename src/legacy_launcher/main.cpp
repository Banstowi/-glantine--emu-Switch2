// SPDX-FileCopyrightText: Copyright 2026 Erable
// SPDX-License-Identifier: GPL-3.0-or-later

// banstdog.exe: the program's name before it became Erable. Older versions update into this
// folder and then start banstdog.exe, and shortcuts still point at it, so it starts erable.exe
// next to it with the same arguments.

#include <string>

#include <windows.h>

int WINAPI wWinMain(HINSTANCE, HINSTANCE, PWSTR, int) {
    std::wstring path(MAX_PATH, L'\0');
    for (;;) {
        const DWORD length = GetModuleFileNameW(nullptr, path.data(), static_cast<DWORD>(path.size()));
        if (length == 0) {
            return 1;
        }
        if (length < path.size()) {
            path.resize(length);
            break;
        }
        path.resize(path.size() * 2);
    }
    path.resize(path.find_last_of(L"\\/") + 1);
    const std::wstring program = path + L"erable.exe";

    // Keeps the arguments exactly as they were quoted: the first token is this program's own path.
    const wchar_t* rest = GetCommandLineW();
    bool quoted = false;
    for (; *rest != L'\0'; ++rest) {
        if (*rest == L'"') {
            quoted = !quoted;
        } else if (!quoted && (*rest == L' ' || *rest == L'\t')) {
            break;
        }
    }
    std::wstring command = L"\"" + program + L"\"" + rest;

    STARTUPINFOW startup{};
    startup.cb = sizeof(startup);
    PROCESS_INFORMATION process{};
    if (!CreateProcessW(program.c_str(), command.data(), nullptr, nullptr, FALSE, 0, nullptr,
                        path.c_str(), &startup, &process)) {
        MessageBoxW(nullptr, L"erable.exe was not found next to banstdog.exe.", L"Erable",
                    MB_OK | MB_ICONERROR);
        return 1;
    }
    CloseHandle(process.hThread);
    CloseHandle(process.hProcess);
    return 0;
}
