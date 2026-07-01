"D:\software\developmentTools\Java\jdk-21.0.10+7\bin\jpackage.exe" ^
  --type app-image ^
  --name zhurong-custom-template ^
  --app-version 0.0.3 ^
  --input D:\heaxo\zhurong-platform\zhurong-platform-custom\zhurong-platform-custom-template\build\libs ^
  --main-jar zhurong-platform-custom-template-0.0.3.jar ^
  --main-class org.springframework.boot.loader.launch.JarLauncher ^
  --dest D:\heaxo\zhurong-platform\zhurong-platform-custom\zhurong-platform-custom-template\build\test1 ^
  --win-console ^
  --java-options "-Dspring.profiles.active=prod"