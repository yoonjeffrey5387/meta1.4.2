import java.util.Date;

/**
 * Estrategia concreta para análisis de sensores de vibración
 * Implementa el patrón Strategy - define algoritmos específicos para detectar
 * anomalías en sensores de vibración
 */
public class EstrategiaAnalisisVibracion implements EstrategiaAnalisis {
    
    // Umbrales de vibración en m/s²
    private static final double UMBRAL_CRITICO = 5.0;
    private static final double UMBRAL_ADVERTENCIA = 3.0;
    
    @Override
    public Alerta analizar(Sensor sensor) {
        // Solo analizar sensores de vibración
        if (!"vibracion".equals(sensor.getTipo())) {
            return null;
        }
        
        double valor = sensor.getValor();
        Date fechaHora = new Date();
        
        // Verificar vibración peligrosa
        if (valor > UMBRAL_CRITICO) {
            return new Alerta(sensor.getId(),
                "🚨 VIBRACIÓN PELIGROSA: " + valor + " m/s² en " + sensor.getUbicacion(),
                NivelAlerta.CRITICO, fechaHora);
        } 
        // Verificar vibración elevada
        else if (valor > UMBRAL_ADVERTENCIA) {
            return new Alerta(sensor.getId(),
                "📳 Vibración elevada: " + valor + " m/s² en " + sensor.getUbicacion(),
                NivelAlerta.ADVERTENCIA, fechaHora);
        }
        
        // No hay alerta si la vibración está dentro de rangos normales
        return null;
    }
}
