/**
 * Interfaz que define el contrato para objetos que pueden ser observados
 * Implementa el patrón Observer - define los métodos que debe implementar
 * cualquier sujeto que quiera notificar a observadores sobre cambios
 */
public interface SujetoAlerta {
    
    /**
     * Registra un nuevo observador para recibir notificaciones
     * @param observador El observador a registrar
     */
    void registrarObservador(ObservadorAlerta observador);
    
    /**
     * Elimina un observador de la lista de notificaciones
     * @param observador El observador a eliminar
     */
    void eliminarObservador(ObservadorAlerta observador);
    
    /**
     * Notifica a todos los observadores registrados sobre una alerta
     * @param alerta La alerta a notificar
     */
    void notificarObservadores(Alerta alerta);
}
