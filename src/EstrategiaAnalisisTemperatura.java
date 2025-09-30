import java.util.Date;

/**
 * Estrategia concreta para análisis de sensores de temperatura
 * Implementa el patrón Strategy - define algoritmos específicos para detectar
 * anomalías en sensores de temperatura
 */
public class EstrategiaAnalisisTemperatura implements EstrategiaAnalisis {
    
    // Umbrales de temperatura en grados Celsius
    private static final double UMBRAL_CRITICO = 80.0;
    private static final double UMBRAL_ADVERTENCIA = 60.0;
    
    @Override
    public Alerta analizar(Sensor sensor) {
        // Solo analizar sensores de temperatura
        if (!"temperatura".equals(sensor.getTipo())) {
            return null;
        }
        
        double valor = sensor.getValor();
        Date fechaHora = new Date();
        
        // Verificar temperatura crítica
        if (valor > UMBRAL_CRITICO) {
            return new Alerta(sensor.getId(), 
                "❌ TEMPERATURA CRÍTICA: " + valor + "°C en " + sensor.getUbicacion(), 
                NivelAlerta.CRITICO, fechaHora);
        } 
        // Verificar temperatura alta (advertencia)
        else if (valor > UMBRAL_ADVERTENCIA) {
            return new Alerta(sensor.getId(),
                "⚠️ Temperatura alta: " + valor + "°C en " + sensor.getUbicacion(),
                NivelAlerta.ADVERTENCIA, fechaHora);
        }
        
        // No hay alerta si la temperatura está dentro de rangos normales
        return null;
    }
}
