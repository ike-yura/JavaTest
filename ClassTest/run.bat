@echo off
setlocal
chcp 65001 >nul

cd /d "%~dp0"

set MAIN=Main

echo コンパイル中...
javac -encoding UTF-8 *.java || goto :error

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
