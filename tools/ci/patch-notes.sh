#!/usr/bin/env bash
# usage: patch-notes.sh [file]
# Prints the body of the first "## " section of PATCHNOTES.md: the notes of the build being made.
set -euo pipefail

file="${1:-PATCHNOTES.md}"
[[ -f "$file" ]] || exit 0
awk '/^## /{ if (seen) exit; seen = 1; next } seen' "$file"
