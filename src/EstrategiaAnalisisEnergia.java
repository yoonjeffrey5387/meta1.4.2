import java.util.Date;

/**
 * Estrategia concreta para análisis de sensores de energía
 * Implementa el patrón Strategy - define algoritmos específicos para detectar
 * anomalías en sensores de consumo energético
 */
public class EstrategiaAnalisisEnergia implements EstrategiaAnalisis {
    
    // Umbral de consumo energético en kW
    private static final double UMBRAL_ENERGIA = 1000.0;
    
    @Override
    public Alerta analizar(Sensor sensor) {
        // Solo analizar sensores de energía
        if (!"energia".equals(sensor.getTipo())) {
            return null;
        }
        
        double valor = sensor.getValor();
        Date fechaHora = new Date();
        
        // Verificar alto consumo energético
        if (valor > UMBRAL_ENERGIA) {
            return new Alerta(sensor.getId(),
                "💡 ALTO CONSUMO ENERGÉTICO: " + valor + " kW en " + sensor.getUbicacion(),
                NivelAlerta.ADVERTENCIA, fechaHora);
        }
        
        // No hay alerta si el consumo está dentro de rangos normales
        return null;
    }
}
