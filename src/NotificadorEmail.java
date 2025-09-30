/**
 * Observador concreto que implementa notificaciones por email
 * Implementa el patrón Observer - reacciona a las alertas enviando emails
 */
public class NotificadorEmail implements ObservadorAlerta {
    
    @Override
    public void actualizar(Alerta alerta) {
        String prefijo = "";
        switch (alerta.getNivel()) {
            case CRITICO: 
                prefijo = "🚨 CRÍTICO - "; 
                break;
            case ADVERTENCIA: 
                prefijo = "⚠️ ADVERTENCIA - "; 
                break;
            case INFORMATIVO: 
                prefijo = "ℹ️ INFORMATIVO - "; 
                break;
        }
        
        System.out.println("📧 ENVIANDO EMAIL - " + prefijo + alerta.getMensaje());
        
        // Aquí se implementaría la lógica real de envío de email
        // Por ejemplo: conectar con un servicio SMTP, configurar destinatarios, etc.
        enviarEmailReal(alerta, prefijo);
    }
    
    @Override
    public String obtenerTipoObservador() {
        return "NotificadorEmail";
    }
    
    /**
     * Método privado que simula el envío real de email
     * @param alerta La alerta a enviar
     * @param prefijo El prefijo según el nivel de criticidad
     */
    private void enviarEmailReal(Alerta alerta, String prefijo) {
        // Simulación de lógica de envío de email
        String destinatario = "admin@fabrica.com";
        String asunto = "Alerta IoT - " + alerta.getNivel();
        String cuerpo = prefijo + alerta.getMensaje() + 
                       "\nSensor: " + alerta.getIdSensor() +
                       "\nFecha: " + alerta.getFechaHora();
        
        // En una implementación real, aquí se usaría JavaMail API o similar
        System.out.println("   → Destinatario: " + destinatario);
        System.out.println("   → Asunto: " + asunto);
        System.out.println("   → Cuerpo: " + cuerpo);
    }
}
