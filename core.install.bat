@echo off
setlocal

cd /d %~dp0

set "JDK21=D:\software\developmentTools\Java\jdk-21.0.10+7"
set "BASE=D:\heaxo\zhurong-platform\zhurong-platform-core\build"
set "INPUT=%BASE%\libs"
set "APPIMG_OUT=%BASE%\app-image"
set "OUTPUT=%BASE%\publish"

set "APP_NAME=zhurong-platform-core"
set "APP_VERSION=0.0.13"
set "MAIN_JAR=zhurong-platform-core-%APP_VERSION%.jar"
set "APPIMG_DIR=%APPIMG_OUT%\%APP_NAME%"

if exist "%APPIMG_OUT%" rmdir /s /q "%APPIMG_OUT%"
if exist "%OUTPUT%" rmdir /s /q "%OUTPUT%"

echo ===== Step 1: build app-image =====
"%JDK21%\bin\jpackage.exe" ^
  --type app-image ^
  --name "%APP_NAME%" ^
  --app-version "%APP_VERSION%" ^
  --input "%INPUT%" ^
  --main-jar "%MAIN_JAR%" ^
  --main-class org.springframework.boot.loader.launch.JarLauncher ^
  --dest "%APPIMG_OUT%" ^
  --win-console ^
  --java-options "-Dspring.profiles.active=prod"

if errorlevel 1 (
  echo.
  echo app-image build failed.
  pause
  exit /b 1
)

echo.
echo ===== Step 2: build exe from app-image =====
"%JDK21%\bin\jpackage.exe" ^
  --type exe ^
  --name "%APP_NAME%" ^
  --app-version "%APP_VERSION%" ^
  --app-image "%APPIMG_DIR%" ^
  --dest "%OUTPUT%" ^
  --win-dir-chooser ^
  --win-menu ^
  --win-shortcut

if errorlevel 1 (
  echo.
  echo exe build failed.
  pause
  exit /b 1
)

echo.
echo Build finished.
echo App image: %APPIMG_DIR%
echo Installer output: %OUTPUT%