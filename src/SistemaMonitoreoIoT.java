/**
 * Clase principal del Sistema de Monitoreo IoT
 * Integra los patrones Singleton, Observer y Strategy para crear un sistema
 * cohesivo de monitoreo de sensores en una fábrica inteligente
 */
public class SistemaMonitoreoIoT {
    
    public static void main(String[] args) {
        System.out.println("=== 🏭 SISTEMA DE MONITOREO IoT - FÁBRICA INTELIGENTE ===\n");
        
        // Configurar el sistema completo
        configurarSistema();
        
        // Simular operaciones del sistema
        simularOperaciones();
        
        System.out.println("\n=== 🏁 SISTEMA FINALIZADO ===");
    }
    
    /**
     * Configura el sistema completo con sensores, observadores y estrategias
     */
    private static void configurarSistema() {
        System.out.println("🔧 Configurando sistema IoT...\n");
        
        // 1. Obtener instancia del GestorSensores (Singleton)
        GestorSensores gestor = GestorSensores.obtenerInstancia();
        NotificadorAlertas notificador = NotificadorAlertas.obtenerInstancia();
        
        // 2. Registrar sensores de diferentes tipos
        System.out.println("📡 Registrando sensores...");
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
        
        // 3. Configurar observadores
        System.out.println("\n👀 Configurando observadores...");
        notificador.registrarObservador(new NotificadorEmail());
        notificador.registrarObservador(new NotificadorDashboard());
        notificador.registrarObservador(new NotificadorSMS());
        notificador.registrarObservador(new RegistradorLogs());
        
        // 4. Configurar estrategia inicial
        notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisTemperatura());
        
        System.out.println("\n✅ Sistema configurado correctamente");
        System.out.println("   - Sensores registrados: " + gestor.obtenerCantidadSensores());
        System.out.println("   - Observadores activos: " + notificador.obtenerCantidadObservadores());
    }
    
    /**
     * Simula operaciones del sistema con diferentes escenarios
     */
    private static void simularOperaciones() {
        System.out.println("\n🔬 Simulando operaciones del sistema...\n");
        
        GestorSensores gestor = GestorSensores.obtenerInstancia();
        NotificadorAlertas notificador = NotificadorAlertas.obtenerInstancia();
        
        try {
            // Escenario 1: Lecturas normales
            System.out.println("--- 📊 Lecturas Normales ---");
            gestor.actualizarValorSensor("TEMP-001", 45.0);
            Thread.sleep(1000);
            
            // Escenario 2: Temperatura crítica
            System.out.println("\n--- 🌡️ Temperatura Crítica ---");
            gestor.actualizarValorSensor("TEMP-001", 85.0);
            Thread.sleep(1000);
            
            // Escenario 3: Cambio de estrategia y vibración peligrosa
            System.out.println("\n--- 🔄 Cambio de Estrategia - Vibración ---");
            notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisVibracion());
            gestor.actualizarValorSensor("VIB-001", 6.5);
            Thread.sleep(1000);
            
            // Escenario 4: Alto consumo energético
            System.out.println("\n--- ⚡ Alto Consumo Energético ---");
            notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisEnergia());
            gestor.actualizarValorSensor("ENER-001", 1200.0);
            Thread.sleep(1000);
            
            // Escenario 5: Calidad del aire crítica
            System.out.println("\n--- 🌫️ Calidad del Aire Crítica ---");
            notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisCalidadAire());
            gestor.actualizarValorSensor("AIR-001", 250.0);
            Thread.sleep(1000);
            
            // Escenario 6: Humedad crítica
            System.out.println("\n--- 💧 Humedad Crítica ---");
            notificador.establecerEstrategiaAnalisis(new EstrategiaAnalisisHumedad());
            gestor.actualizarValorSensor("HUM-001", 95.0);
            Thread.sleep(1000);
            
            // Mostrar estadísticas finales
            mostrarEstadisticasFinales(gestor, notificador);
            
        } catch (InterruptedException e) {
            System.err.println("❌ Error en la simulación: " + e.getMessage());
        }
    }
    
    /**
     * Muestra las estadísticas finales del sistema
     */
    private static void mostrarEstadisticasFinales(GestorSensores gestor, NotificadorAlertas notificador) {
        System.out.println("\n=== 📊 ESTADÍSTICAS FINALES ===");
        System.out.println("Sensores registrados: " + gestor.obtenerCantidadSensores());
        System.out.println("Observadores activos: " + notificador.obtenerCantidadObservadores());
        System.out.println("Estrategia actual: " + notificador.obtenerEstrategiaActual().getClass().getSimpleName());
        
        // Mostrar estadísticas detalladas
        System.out.println("\n📈 Estadísticas detalladas:");
        var estadisticas = gestor.obtenerEstadisticas();
        System.out.println("   - Total sensores: " + estadisticas.get("totalSensores"));
        System.out.println("   - Sensores por tipo: " + estadisticas.get("sensoresPorTipo"));
        System.out.println("   - Valores promedio: " + estadisticas.get("valoresPromedio"));
        
        // Mostrar logs si hay un RegistradorLogs
        for (ObservadorAlerta observador : notificador.obtenerObservadores()) {
            if (observador instanceof RegistradorLogs) {
                RegistradorLogs registrador = (RegistradorLogs) observador;
                System.out.println("\n📝 Logs registrados (" + registrador.obtenerCantidadLogs() + "):");
                for (String log : registrador.obtenerLogs()) {
                    System.out.println("   " + log);
                }
                break;
            }
        }
    }
}
