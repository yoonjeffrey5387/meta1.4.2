import java.util.Date;

/**
 * Estrategia concreta para análisis de sensores de calidad del aire
 * Implementa el patrón Strategy - define algoritmos específicos para detectar
 * anomalías en sensores de calidad del aire
 */
public class EstrategiaAnalisisCalidadAire implements EstrategiaAnalisis {
    
    // Umbrales de calidad del aire (índice de calidad del aire)
    private static final double UMBRAL_CRITICO = 200.0;  // AQI > 200 (muy insalubre)
    private static final double UMBRAL_ADVERTENCIA = 100.0; // AQI > 100 (insalubre)
    
    @Override
    public Alerta analizar(Sensor sensor) {
        // Solo analizar sensores de calidad del aire
        if (!"calidad_aire".equals(sensor.getTipo())) {
            return null;
        }
        
        double valor = sensor.getValor();
        Date fechaHora = new Date();
        
        // Verificar calidad del aire crítica
        if (valor > UMBRAL_CRITICO) {
            return new Alerta(sensor.getId(),
                "🌫️ CALIDAD DEL AIRE CRÍTICA: AQI " + valor + " en " + sensor.getUbicacion(),
                NivelAlerta.CRITICO, fechaHora);
        } 
        // Verificar calidad del aire insalubre
        else if (valor > UMBRAL_ADVERTENCIA) {
            return new Alerta(sensor.getId(),
                "⚠️ Calidad del aire insalubre: AQI " + valor + " en " + sensor.getUbicacion(),
                NivelAlerta.ADVERTENCIA, fechaHora);
        }
        
        // No hay alerta si la calidad del aire está dentro de rangos normales
        return null;
    }
}
