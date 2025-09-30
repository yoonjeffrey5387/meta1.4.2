/**
 * Interfaz que define el contrato para objetos que quieren ser notificados
 * Implementa el patrón Observer - define los métodos que debe implementar
 * cualquier observador que quiera recibir notificaciones sobre alertas
 */
public interface ObservadorAlerta {
    
    /**
     * Método llamado cuando el sujeto notifica sobre una alerta
     * @param alerta La alerta que se debe procesar
     */
    void actualizar(Alerta alerta);
    
    /**
     * Obtiene el tipo de observador para identificación
     * @return String que identifica el tipo de observador
     */
    String obtenerTipoObservador();
}
