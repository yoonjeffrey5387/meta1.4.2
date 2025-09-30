import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Observador concreto que implementa el registro de logs
 * Implementa el patrón Observer - reacciona a las alertas registrándolas en logs
 */
public class RegistradorLogs implements ObservadorAlerta {
    private List<String> logs;
    private SimpleDateFormat formatter;
    
    /**
     * Constructor que inicializa el registrador de logs
     */
    public RegistradorLogs() {
        this.logs = new ArrayList<>();
        this.formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    
    @Override
    public void actualizar(Alerta alerta) {
        String logEntry = String.format("[%s] %s - %s", 
            formatter.format(alerta.getFechaHora()),
            alerta.getNivel(),
            alerta.getMensaje());
        
        logs.add(logEntry);
        System.out.println("📝 REGISTRANDO LOG - " + logEntry);
        
        // Aquí se implementaría la lógica real de escritura de logs
        escribirLogReal(logEntry);
    }
    
    @Override
    public String obtenerTipoObservador() {
        return "RegistradorLogs";
    }
    
    /**
     * Obtiene todos los logs registrados
     * @return Lista con todos los logs
     */
    public List<String> obtenerLogs() {
        return new ArrayList<>(logs);
    }
    
    /**
     * Obtiene los logs de un nivel específico
     * @param nivel El nivel de alerta a filtrar
     * @return Lista de logs del nivel especificado
     */
    public List<String> obtenerLogsPorNivel(NivelAlerta nivel) {
        List<String> logsFiltrados = new ArrayList<>();
        for (String log : logs) {
            if (log.contains(nivel.toString())) {
                logsFiltrados.add(log);
            }
        }
        return logsFiltrados;
    }
    
    /**
     * Limpia todos los logs registrados
     */
    public void limpiarLogs() {
        logs.clear();
        System.out.println("🗑️ Logs limpiados");
    }
    
    /**
     * Obtiene la cantidad de logs registrados
     * @return Número de logs registrados
     */
    public int obtenerCantidadLogs() {
        return logs.size();
    }
    
    /**
     * Método privado que simula la escritura real de logs
     * @param logEntry La entrada de log a escribir
     */
    private void escribirLogReal(String logEntry) {
        // Simulación de escritura de logs
        System.out.println("   → Escribiendo en archivo de log: sistema_iot.log");
        System.out.println("   → Nivel de log: " + (logEntry.contains("CRITICO") ? "ERROR" : "WARN"));
        
        // En una implementación real, aquí se usaría Log4j, SLF4J, o similar
    }
}
