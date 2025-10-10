@echo off
setlocal
chcp 65001 >nul

rem このbatのあるフォルダに移動
cd /d "%~dp0"

rem ここを実行したいクラス名に
set "MAIN=BaseTest"

rem 1) コンパイル（UTF-8明示）
javac -encoding UTF-8 "%MAIN%.java" || goto :end

rem 2) 実行（UTF-8明示）
java -Dfile.encoding=UTF-8 -cp "%cd%" %MAIN%

:end
echo.
pause
