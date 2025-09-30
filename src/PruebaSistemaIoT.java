/**
 * Clase de pruebas completa para validar el funcionamiento del Sistema de Monitoreo IoT
 * Demuestra la integración de los patrones Singleton, Observer y Strategy
 */
public class PruebaSistemaIoT {
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== 🧪 INICIO PRUEBA SISTEMA IoT ===\n");
        
        // Configuración inicial
        GestorSensores gestor = GestorSensores.obtenerInstancia();
        NotificadorAlertas notificador = NotificadorAlertas.obtenerInstancia();
        
        // Registrar observadores
        System.out.println("👀 Registrando observadores...");
        notificador.registrarObservador(new NotificadorEmail());
        notificador.registrarObservador(new NotificadorDashboard());
        notificador.registrarObservador(new NotificadorSMS());
        RegistradorLogs registradorLogs = new RegistradorLogs();
        notificador.registrarObservador(registradorLogs);
        
        // Crear sensores
        System.out.println("\n📡 Creando sensores...");
        Sensor sensorTemperatura = new Sensor("TEMP-001", "temperatura", 25.0, "Sala de Máquinas A");
        Sensor sensorVibracion = new Sensor("VIB-001", "vibracion", 2.0, "Motor Principal");
        Sensor sensorEnergia = new Sensor("ENER-001", "energia", 800.0, "Subestación Eléctrica");
        Sensor sensorCalidadAire = new Sensor("AIR-001", "calidad_aire", 50.0, "Área de Producción");
        Sensor sensorHumedad = new Sensor("HUM-001", "humedad", 45.0, "Almacén de Materiales");
        
        gestor.registrarSensor(sensorTemperatura);
        gestor.registrarSensor(sensorVibracion);
        gestor.registrarSensor(sensorEnergia);
        gestor.registrarSensor(sensorCalidadAire);
        gestor.registrarSensor(sensorHumedad);
        
        System.out.println("\n=== 🔬 SIMULANDO LECTURAS DE SENSORES ===\n");
        
        // Simular lecturas normales
        System.out.println("--- 📊 Lecturas Normales ---");
        gestor.actualizarValorSensor("TEMP-001", 45.0);
        Thread.sleep(1000);
        
        // Temperatura crítica
        System.out.println("\n--- 🌡️ Temperatura Crítica ---");
        gestor.actualizarValorSensor("TEMP-001", 85.0);
        Thread.sleep(1000);
        
        // Vibración peligrosa
        System.out.println("\n--- 📳 Vibración Peligrosa ---");
        notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisVibracion());
        gestor.actualizarValorSensor("VIB-001", 6.5);
        Thread.sleep(1000);
        
        // Cambiar estrategia en tiempo de ejecución
        System.out.println("\n--- 🔄 Cambio de Estrategia - Energía ---");
        notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisEnergia());
        gestor.actualizarValorSensor("ENER-001", 3.5);
        Thread.sleep(1000);
        
        // Alto consumo energético
        System.out.println("\n--- ⚡ Alto Consumo Energético ---");
        gestor.actualizarValorSensor("ENER-001", 1200.0);
        Thread.sleep(1000);
        
        // Calidad del aire crítica
        System.out.println("\n--- 🌫️ Calidad del Aire Crítica ---");
        notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisCalidadAire());
        gestor.actualizarValorSensor("AIR-001", 250.0);
        Thread.sleep(1000);
        
        // Humedad crítica
        System.out.println("\n--- 💧 Humedad Crítica ---");
        notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisHumedad());
        gestor.actualizarValorSensor("HUM-001", 95.0);
        Thread.sleep(1000);
        
        // Pruebas adicionales de funcionalidad
        realizarPruebasAdicionales(gestor, notificador, registradorLogs);
        
        System.out.println("\n=== 📊 ESTADÍSTICAS FINALES ===");
        System.out.println("Sensores registrados: " + gestor.obtenerCantidadSensores());
        System.out.println("Observadores activos: " + notificador.obtenerCantidadObservadores());
        System.out.println("Estrategia actual: " + notificador.obtenerEstrategiaActual().getClass().getSimpleName());
        
        // Mostrar estadísticas detalladas
        mostrarEstadisticasDetalladas(gestor, registradorLogs);
        
        System.out.println("\n=== 🏁 FIN PRUEBA SISTEMA IoT ===");
    }
    
    /**
     * Realiza pruebas adicionales de funcionalidad del sistema
     */
    private static void realizarPruebasAdicionales(GestorSensores gestor, NotificadorAlertas notificador, RegistradorLogs registradorLogs) {
        System.out.println("\n--- 🔧 Pruebas Adicionales ---");
        
        // Prueba 1: Verificar Singleton
        System.out.println("✅ Verificando patrón Singleton...");
        GestorSensores gestor2 = GestorSensores.obtenerInstancia();
        NotificadorAlertas notificador2 = NotificadorAlertas.obtenerInstancia();
        System.out.println("   - GestorSensores es la misma instancia: " + (gestor == gestor2));
        System.out.println("   - NotificadorAlertas es la misma instancia: " + (notificador == notificador2));
        
        // Prueba 2: Obtener sensores por tipo
        System.out.println("\n✅ Probando filtrado por tipo...");
        var sensoresTemperatura = gestor.obtenerSensoresPorTipo("temperatura");
        System.out.println("   - Sensores de temperatura: " + sensoresTemperatura.size());
        
        // Prueba 3: Verificar duplicados en observadores
        System.out.println("\n✅ Probando prevención de duplicados...");
        notificador.registrarObservador(new NotificadorEmail()); // Intentar duplicado
        System.out.println("   - Observadores después del intento de duplicado: " + notificador.obtenerCantidadObservadores());
        
        // Prueba 4: Eliminar observador
        System.out.println("\n✅ Probando eliminación de observador...");
        NotificadorDashboard dashboard = new NotificadorDashboard();
        notificador.registrarObservador(dashboard);
        System.out.println("   - Observadores antes de eliminar: " + notificador.obtenerCantidadObservadores());
        notificador.eliminarObservador(dashboard);
        System.out.println("   - Observadores después de eliminar: " + notificador.obtenerCantidadObservadores());
        
        // Prueba 5: Estrategia básica (sin alertas)
        System.out.println("\n✅ Probando estrategia básica...");
        notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisBasica());
        gestor.actualizarValorSensor("TEMP-001", 90.0); // Temperatura que normalmente generaría alerta
        System.out.println("   - No se generaron alertas con estrategia básica");
    }
    
    /**
     * Muestra estadísticas detalladas del sistema
     */
    private static void mostrarEstadisticasDetalladas(GestorSensores gestor, RegistradorLogs registradorLogs) {
        System.out.println("\n📈 Estadísticas detalladas:");
        var estadisticas = gestor.obtenerEstadisticas();
        System.out.println("   - Total sensores: " + estadisticas.get("totalSensores"));
        System.out.println("   - Sensores por tipo: " + estadisticas.get("sensoresPorTipo"));
        System.out.println("   - Valores promedio: " + estadisticas.get("valoresPromedio"));
        
        // Mostrar logs registrados
        System.out.println("\n📝 Logs registrados (" + registradorLogs.obtenerCantidadLogs() + "):");
        for (String log : registradorLogs.obtenerLogs()) {
            System.out.println("   " + log);
        }
        
        // Mostrar logs por nivel
        System.out.println("\n📊 Logs por nivel:");
        System.out.println("   - Críticos: " + registradorLogs.obtenerLogsPorNivel(NivelAlerta.CRITICO).size());
        System.out.println("   - Advertencias: " + registradorLogs.obtenerLogsPorNivel(NivelAlerta.ADVERTENCIA).size());
        System.out.println("   - Informativos: " + registradorLogs.obtenerLogsPorNivel(NivelAlerta.INFORMATIVO).size());
    }
}
