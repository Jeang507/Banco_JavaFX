#!/bin/bash

echo "Limpiando build anterior..."
rm -rf out

echo "Creando estructura de salida..."
mkdir -p out/banco/views
mkdir -p out/banco/styles

echo "Compilando código Java..."
javac \
    --module-path ~/javafx/lib \
    --add-modules javafx.controls,javafx.fxml,javafx.web \
    -cp "lib/postgresql-42.7.8.jar" \
    $(find src -name "*.java") \
    -d out

echo "Copiando vistas (FXML + HTML)..."
cp src/banco/views/* out/banco/views/

echo "Copiando estilos..."
cp src/banco/styles/* out/banco/styles/

echo "Ejecutando aplicación..."

java \
    --module-path ~/javafx/lib \
    --add-modules javafx.controls,javafx.fxml,javafx.web \
    --enable-native-access=javafx.graphics \
    -cp "out:lib/postgresql-42.7.8.jar" \
    banco.Banco
