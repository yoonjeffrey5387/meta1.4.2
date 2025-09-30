/**
 * Observador concreto que implementa actualizaciones del dashboard
 * Implementa el patrón Observer - reacciona a las alertas actualizando la interfaz de usuario
 */
public class NotificadorDashboard implements ObservadorAlerta {
    
    @Override
    public void actualizar(Alerta alerta) {
        System.out.println("📊 ACTUALIZANDO DASHBOARD - " + alerta.getMensaje());
        
        // Aquí se implementaría la lógica real de actualización del dashboard
        // Por ejemplo: actualizar gráficos, tablas, indicadores en tiempo real
        actualizarDashboardReal(alerta);
    }
    
    @Override
    public String obtenerTipoObservador() {
        return "NotificadorDashboard";
    }
    
    /**
     * Método privado que simula la actualización real del dashboard
     * @param alerta La alerta a mostrar en el dashboard
     */
    private void actualizarDashboardReal(Alerta alerta) {
        // Simulación de actualización del dashboard
        System.out.println("   → Actualizando gráfico de alertas en tiempo real");
        System.out.println("   → Refrescando tabla de sensores");
        System.out.println("   → Actualizando indicadores de estado");
        
        // En una implementación real, aquí se usaría WebSocket, Server-Sent Events,
        // o algún framework como Spring WebFlux para actualizaciones en tiempo real
    }
}
