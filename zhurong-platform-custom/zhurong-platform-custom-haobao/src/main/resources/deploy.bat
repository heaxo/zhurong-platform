"D:\software\developmentTools\Java\jdk-21.0.10+7\bin\jpackage.exe" ^
  --type app-image ^
  --name zhurong-custom-haobao ^
  --app-version 0.0.1 ^
  --input D:\heaxo\zhurong-platform\zhurong-platform-custom\zhurong-platform-custom-haobao\build\libs ^
  --main-jar zhurong-platform-custom-haobao-0.0.1.jar ^
  --main-class org.springframework.boot.loader.launch.JarLauncher ^
  --dest D:\heaxo\zhurong-platform\zhurong-platform-custom\zhurong-platform-custom-haobao\build\test1 ^
  --win-console ^
  --java-options "-Dspring.profiles.active=prod"