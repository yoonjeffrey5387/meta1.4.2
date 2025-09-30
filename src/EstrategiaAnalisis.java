/**
 * Interfaz que define el contrato para estrategias de análisis de datos de sensores
 * Implementa el patrón Strategy - permite definir diferentes algoritmos de análisis
 * que pueden ser intercambiados dinámicamente según el tipo de sensor
 */
public interface EstrategiaAnalisis {
    
    /**
     * Analiza los datos de un sensor y determina si debe generar una alerta
     * @param sensor El sensor cuyos datos se van a analizar
     * @return Una Alerta si se detecta una anomalía, null en caso contrario
     */
    Alerta analizar(Sensor sensor);
}
