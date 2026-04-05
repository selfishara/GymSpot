param(
    [string]$DiffFile = ".openclaw_pr_diff.txt",
    [string]$PromptFile = ".openclaw_pr_prompt.txt"
)

$ErrorActionPreference = "Stop"

function Write-Info($msg) {
    Write-Host "[prgn] $msg" -ForegroundColor Cyan
}

function Fail($msg) {
    Write-Host "[prgn] ERROR: $msg" -ForegroundColor Red
    exit 1
}

# Check git repository
git rev-parse --is-inside-work-tree 2>$null | Out-Null
if ($LASTEXITCODE -ne 0) {
    Fail "This folder is not a Git repository."
}

# Check staged changes
$stagedNames = git diff --staged --name-only
if (-not $stagedNames) {
    Fail "No staged changes found. Run 'git add ...' first."
}

Write-Info "Collecting staged diff..."
$diff = git --no-pager diff --staged
if (-not $diff) {
    Fail "Staged diff is empty."
}

# Save raw diff
Set-Content -Path $DiffFile -Value $diff -Encoding UTF8
Write-Info "Saved diff to $DiffFile"

# Build strict prompt
$prompt = @"
You are a strict GitHub Pull Request writer for the GymSpot repository.

Your previous outputs contained invalid assumptions.
You invented files, used incorrect paths, mislabeled changes, and added generic filler text.

You must correct those mistakes now.

Mandatory rules:
- Use ONLY the git diff provided below
- Do NOT invent files, features, tests, architecture, runtime behavior, or implementation details
- Use file paths EXACTLY as they appear in the diff
- Any incorrect path makes the output invalid
- Distinguish correctly between:
  - added file
  - modified file
  - deleted file
- Do NOT call an added file an update
- If the diff is documentation, tooling, config, workspace setup, or AI guidance, say it clearly
- Do NOT label documentation/tooling/config changes as [FEATURE]
- Use the correct title label based only on the diff: [CHORE], [DOCS], [REFACTOR], or [FEATURE]
- Do NOT paste the full file content
- Summarize the change instead of reproducing the file
- Do NOT include generic filler text, workflow explanations, or closing phrases
- Do NOT include sentences like "By following these steps..."
- If no testing applies, write exactly: "No testing required"
- If something is uncertain, state it clearly instead of guessing

Validation rules before answering:
- exact paths
- correct label
- correct add/update/delete classification
- nothing invented
- no file content pasted
- no filler text

If any rule is violated, the output is invalid.

Output format:

## Title

## Description

## Changes
- only real changes from the diff

## Technical Details

## Testing Notes

## Risks / Review Points

Git diff:
$diff
"@

Set-Content -Path $PromptFile -Value $prompt -Encoding UTF8
Write-Info "Saved prompt to $PromptFile"

Write-Host ""
Write-Host "Files staged:" -ForegroundColor Yellow
git diff --staged --name-only

Write-Host ""
Write-Host "Next step:" -ForegroundColor Green
Write-Host "1. Open $PromptFile"
Write-Host "2. Copy all contents"
Write-Host "3. Paste into OpenClaw"
Write-Host ""
Write-Host "Done."