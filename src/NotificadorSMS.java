/**
 * Observador concreto que implementa notificaciones por SMS
 * Implementa el patrón Observer - reacciona a las alertas enviando SMS solo para alertas críticas
 */
public class NotificadorSMS implements ObservadorAlerta {
    
    @Override
    public void actualizar(Alerta alerta) {
        // Solo enviar SMS para alertas críticas
        if (alerta.getNivel() == NivelAlerta.CRITICO) {
            System.out.println("📱 ENVIANDO SMS CRÍTICO - " + alerta.getMensaje());
            
            // Aquí se implementaría la lógica real de envío de SMS
            enviarSMSReal(alerta);
        } else {
            System.out.println("📱 SMS no enviado - Nivel no crítico: " + alerta.getNivel());
        }
    }
    
    @Override
    public String obtenerTipoObservador() {
        return "NotificadorSMS";
    }
    
    /**
     * Método privado que simula el envío real de SMS
     * @param alerta La alerta crítica a enviar por SMS
     */
    private void enviarSMSReal(Alerta alerta) {
        // Simulación de lógica de envío de SMS
        String numeroDestino = "+1234567890"; // Número del supervisor
        String mensaje = "ALERTA CRÍTICA: " + alerta.getMensaje() + 
                        " - Sensor: " + alerta.getIdSensor();
        
        System.out.println("   → Número destino: " + numeroDestino);
        System.out.println("   → Mensaje: " + mensaje);
        
        // En una implementación real, aquí se usaría un servicio como Twilio, AWS SNS, etc.
    }
}
