@echo off
setlocal
chcp 65001 >nul

rem --- バッチがあるフォルダに移動 ---
cd /d "%~dp0"

rem --- 実行するメインクラス名（大文字に注意） ---
set MAIN=Main

rem --- 1. コンパイル ---
echo コンパイル中...
javac -encoding UTF-8 "%MAIN%.java" Counter.java || goto :error

rem --- 2. 実行 ---
echo 実行中...
java -Dfile.encoding=UTF-8 -cp "%cd%" %MAIN%
goto :end

:error
echo.
echo コンパイルエラーが発生しました。
pause
goto :eof

:end
echo.
pause
