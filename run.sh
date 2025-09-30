#!/bin/bash

# Script de ejecución para el Sistema de Monitoreo IoT
# Ejecuta la aplicación principal del sistema

echo "🏭 Iniciando Sistema de Monitoreo IoT..."

# Verificar si el directorio bin existe
if [ ! -d "bin" ]; then
    echo "❌ Directorio bin/ no encontrado"
    echo "🔧 Ejecutando compilación automática..."
    ./compile.sh
    if [ $? -ne 0 ]; then
        echo "❌ Error en la compilación. No se puede ejecutar."
        exit 1
    fi
fi

# Verificar si existen archivos .class
if [ ! -f "bin/SistemaMonitoreoIoT.class" ]; then
    echo "❌ Archivos compilados no encontrados"
    echo "🔧 Ejecutando compilación automática..."
    ./compile.sh
    if [ $? -ne 0 ]; then
        echo "❌ Error en la compilación. No se puede ejecutar."
        exit 1
    fi
fi

echo "🚀 Ejecutando Sistema de Monitoreo IoT..."
echo "=========================================="
echo ""

# Cambiar al directorio bin y ejecutar
cd bin
java SistemaMonitoreoIoT

# Verificar si la ejecución fue exitosa
if [ $? -eq 0 ]; then
    echo ""
    echo "=========================================="
    echo "✅ Sistema ejecutado correctamente"
    echo "🧪 Para ejecutar las pruebas: ./test.sh"
else
    echo ""
    echo "=========================================="
    echo "❌ Error en la ejecución del sistema"
    echo "🔍 Revisa los errores arriba"
fi

# Volver al directorio raíz
cd ..

