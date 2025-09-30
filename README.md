# 🏭 Sistema de Monitoreo IoT - Meta 1.4.2

## 🚀 Ejecución del Sistema

### 1. Compilar todo
```bash
./compile.sh
```

### 2. Ver el sistema funcionando
```bash
./run.sh
```

### 3. Ejecutar las pruebas completas
```bash
./test.sh
```

## 📊 Diagramas del Sistema

### Diagramas de Patrones de Diseño

1. **`diagrama.puml`** - Diagrama principal del sistema completo
2. **`diagrama_strategy.puml`** - Patrón Strategy (Estrategias de Análisis)
3. **`diagrama_observer.puml`** - Patrón Observer (Sistema de Notificaciones)
4. **`diagrama_singleton.puml`** - Patrón Singleton (Gestión Centralizada)
5. **`diagrama_secuencia.puml`** - Diagrama de secuencia del flujo del sistema
6. **`diagrama_arquitectura.puml`** - Arquitectura general del sistema

### 🎯 Patrones de Diseño Implementados

- **Singleton**: Gestión centralizada de sensores y notificaciones
- **Observer**: Sistema de notificaciones automáticas
- **Strategy**: Estrategias de análisis por tipo de sensor

### 🔧 Tecnologías Utilizadas

- **Java**: Lenguaje de programación
- **PlantUML**: Diagramas UML
- **Git**: Control de versiones
- **GitHub**: Repositorio remoto

### 📁 Estructura del Proyecto

```
src/           # Código fuente Java
bin/           # Archivos compilados (.class)
diagrama*.puml # Diagramas PlantUML
*.sh          # Scripts de automatización
```

### 🎓 Autor

**Yoon Jeffrey** - Meta 1.4.2