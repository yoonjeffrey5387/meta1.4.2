import java.util.Date;

/**
 * Estrategia concreta para análisis de sensores de humedad
 * Implementa el patrón Strategy - define algoritmos específicos para detectar
 * anomalías en sensores de humedad
 */
public class EstrategiaAnalisisHumedad implements EstrategiaAnalisis {
    
    // Umbrales de humedad en porcentaje
    private static final double UMBRAL_CRITICO_ALTO = 90.0;  // Humedad muy alta
    private static final double UMBRAL_CRITICO_BAJO = 10.0;  // Humedad muy baja
    private static final double UMBRAL_ADVERTENCIA_ALTO = 80.0; // Humedad alta
    private static final double UMBRAL_ADVERTENCIA_BAJO = 20.0; // Humedad baja
    
    @Override
    public Alerta analizar(Sensor sensor) {
        // Solo analizar sensores de humedad
        if (!"humedad".equals(sensor.getTipo())) {
            return null;
        }
        
        double valor = sensor.getValor();
        Date fechaHora = new Date();
        
        // Verificar humedad crítica alta
        if (valor > UMBRAL_CRITICO_ALTO) {
            return new Alerta(sensor.getId(),
                "💧 HUMEDAD CRÍTICA ALTA: " + valor + "% en " + sensor.getUbicacion(),
                NivelAlerta.CRITICO, fechaHora);
        } 
        // Verificar humedad crítica baja
        else if (valor < UMBRAL_CRITICO_BAJO) {
            return new Alerta(sensor.getId(),
                "🏜️ HUMEDAD CRÍTICA BAJA: " + valor + "% en " + sensor.getUbicacion(),
                NivelAlerta.CRITICO, fechaHora);
        }
        // Verificar humedad alta (advertencia)
        else if (valor > UMBRAL_ADVERTENCIA_ALTO) {
            return new Alerta(sensor.getId(),
                "⚠️ Humedad alta: " + valor + "% en " + sensor.getUbicacion(),
                NivelAlerta.ADVERTENCIA, fechaHora);
        }
        // Verificar humedad baja (advertencia)
        else if (valor < UMBRAL_ADVERTENCIA_BAJO) {
            return new Alerta(sensor.getId(),
                "⚠️ Humedad baja: " + valor + "% en " + sensor.getUbicacion(),
                NivelAlerta.ADVERTENCIA, fechaHora);
        }
        
        // No hay alerta si la humedad está dentro de rangos normales
        return null;
    }
}
