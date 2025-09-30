#!/bin/bash

# Script de compilación para el Sistema de Monitoreo IoT
# Compila todos los archivos Java del directorio src/ y los coloca en bin/

echo "🔧 Compilando Sistema de Monitoreo IoT..."

# Crear directorio bin si no existe
mkdir -p bin

# Compilar todos los archivos Java
echo "📦 Compilando archivos Java..."
javac -d bin src/*.java

# Verificar si la compilación fue exitosa
if [ $? -eq 0 ]; then
    echo "✅ Compilación exitosa!"
    echo "📁 Archivos compilados en: bin/"
    echo "📊 Archivos generados:"
    ls -la bin/*.class | wc -l | xargs echo "   - Total de archivos .class:"
    echo ""
    echo "🚀 Para ejecutar el sistema:"
    echo "   ./run.sh          # Aplicación principal"
    echo "   ./test.sh         # Pruebas completas"
else
    echo "❌ Error en la compilación"
    echo "🔍 Revisa los errores arriba"
    exit 1
fi


