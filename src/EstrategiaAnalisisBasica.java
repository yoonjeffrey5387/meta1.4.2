/**
 * Estrategia básica que no genera alertas
 * Implementa el patrón Strategy - estrategia por defecto que no detecta anomalías
 * Útil para desactivar temporalmente el análisis de alertas
 */
public class EstrategiaAnalisisBasica implements EstrategiaAnalisis {
    
    @Override
    public Alerta analizar(Sensor sensor) {
        // Estrategia básica que no genera alertas
        // Útil para desactivar temporalmente el análisis
        return null;
    }
}
