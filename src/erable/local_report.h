// SPDX-FileCopyrightText: Copyright 2026 Eden Emulator Project
// SPDX-License-Identifier: GPL-3.0-or-later

#pragma once

#include <QDialog>
#include <QString>

#include "erable/compatibility_report.h"

class QComboBox;
class QLabel;
class QPlainTextEdit;

namespace LocalReport {

/// A compatibility report kept on this PC: saved as a text file in the "reports" directory of the
/// user data, and sent by e-mail only when the player asks, through their own mail program.
class Dialog final : public QDialog {
public:
    explicit Dialog(const CompatibilityReporting::Session& session, QWidget* parent = nullptr);

private:
    [[nodiscard]] QString ReportText() const;
    bool Save();
    void SendByEmail();
    void OpenFolder();

    CompatibilityReporting::Session session;
    QComboBox* outcome;
    QPlainTextEdit* notes;
    QLabel* status;
    QString saved_path;
};

/// The directory reports are saved in.
[[nodiscard]] QString ReportsDirectory();

} // namespace LocalReport
