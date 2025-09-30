#!/bin/bash

# Script de pruebas para el Sistema de Monitoreo IoT
# Ejecuta las pruebas completas del sistema

echo "🧪 Iniciando Pruebas del Sistema IoT..."

# Verificar si el directorio bin existe
if [ ! -d "bin" ]; then
    echo "❌ Directorio bin/ no encontrado"
    echo "🔧 Ejecutando compilación automática..."
    ./compile.sh
    if [ $? -ne 0 ]; then
        echo "❌ Error en la compilación. No se pueden ejecutar las pruebas."
        exit 1
    fi
fi

# Verificar si existen archivos .class
if [ ! -f "bin/PruebaSistemaIoT.class" ]; then
    echo "❌ Archivos de prueba no encontrados"
    echo "🔧 Ejecutando compilación automática..."
    ./compile.sh
    if [ $? -ne 0 ]; then
        echo "❌ Error en la compilación. No se pueden ejecutar las pruebas."
        exit 1
    fi
fi

echo "🔬 Ejecutando Pruebas Completas del Sistema..."
echo "=============================================="
echo ""

# Cambiar al directorio bin y ejecutar las pruebas
cd bin
java PruebaSistemaIoT

# Verificar si las pruebas fueron exitosas
if [ $? -eq 0 ]; then
    echo ""
    echo "=============================================="
    echo "✅ Todas las pruebas ejecutadas correctamente"
    echo ""
    echo "📊 Resumen de pruebas:"
    echo "   🎯 Singleton: Verificado"
    echo "   👀 Observer: Verificado" 
    echo "   🧠 Strategy: Verificado"
    echo "   🔍 Filtrado: Verificado"
    echo "   🚫 Duplicados: Verificado"
    echo ""
    echo "🏭 Para ejecutar la aplicación: ./run.sh"
else
    echo ""
    echo "=============================================="
    echo "❌ Error en la ejecución de las pruebas"
    echo "🔍 Revisa los errores arriba"
fi

# Volver al directorio raíz
cd ..

