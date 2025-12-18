@echo off

echo Limpiando build anterior...
rmdir /s /q out

echo Creando estructura de salida...
mkdir out\banco\views
mkdir out\banco\styles

echo Compilando codigo Java...
javac ^
  --module-path "%USERPROFILE%\javafx\lib" ^
  --add-modules javafx.controls,javafx.fxml,javafx.web ^
  -cp "lib\postgresql-42.7.8.jar" ^
  src\banco\**\*.java ^
  -d out

echo Copiando vistas...
xcopy src\banco\views out\banco\views /E /I /Y

echo Copiando estilos...
xcopy src\banco\styles out\banco\styles /E /I /Y

echo Ejecutando aplicacion...
java ^
  --module-path "%USERPROFILE%\javafx\lib" ^
  --add-modules javafx.controls,javafx.fxml,javafx.web ^
  --enable-native-access=javafx.graphics ^
  -cp "out;lib\postgresql-42.7.8.jar" ^
  banco.Banco
