import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Clase que implementa el patrón Singleton para gestionar centralizadamente todos los sensores IoT
 * Garantiza que solo exista una instancia de esta clase en toda la aplicación
 * Proporciona métodos para registrar, actualizar, consultar y eliminar sensores
 */
public class GestorSensores {
    private static GestorSensores instancia;
    private Map<String, Sensor> sensores;
    
    /**
     * Constructor privado para implementar el patrón Singleton
     * Inicializa el mapa de sensores como ConcurrentHashMap para thread-safety
     */
    private GestorSensores() {
        this.sensores = new ConcurrentHashMap<>();
        System.out.println("🔧 Gestor de Sensores inicializado");
    }
    
    /**
     * Método estático para obtener la instancia única del GestorSensores
     * Implementa double-checked locking para thread-safety
     * @return La instancia única del GestorSensores
     */
    public static GestorSensores obtenerInstancia() {
        if (instancia == null) {
            synchronized (GestorSensores.class) {
                if (instancia == null) {
                    instancia = new GestorSensores();
                }
            }
        }
        return instancia;
    }
    
    /**
     * Registra un nuevo sensor en el sistema
     * @param sensor El sensor a registrar
     */
    public void registrarSensor(Sensor sensor) {
        sensores.put(sensor.getId(), sensor);
        System.out.println("✅ Sensor registrado: " + sensor.getId() + " en " + sensor.getUbicacion());
    }
    
    /**
     * Actualiza el valor de un sensor y notifica a los observadores
     * @param idSensor ID del sensor a actualizar
     * @param nuevoValor Nuevo valor medido por el sensor
     */
    public void actualizarValorSensor(String idSensor, double nuevoValor) {
        Sensor sensor = sensores.get(idSensor);
        if (sensor != null) {
            sensor.setValor(nuevoValor);
            sensor.setUltimaActualizacion(new Date());
            System.out.println("📊 Sensor " + idSensor + " actualizado: " + nuevoValor);
            
            // Notificar a los observadores a través del NotificadorAlertas
            NotificadorAlertas.obtenerInstancia().verificarYNotificar(sensor);
        } else {
            System.out.println("❌ Sensor no encontrado: " + idSensor);
        }
    }
    
    /**
     * Obtiene un sensor por su ID
     * @param idSensor ID del sensor a buscar
     * @return El sensor encontrado o null si no existe
     */
    public Sensor obtenerSensor(String idSensor) {
        return sensores.get(idSensor);
    }
    
    /**
     * Elimina un sensor del sistema
     * @param idSensor ID del sensor a eliminar
     */
    public void eliminarSensor(String idSensor) {
        sensores.remove(idSensor);
        System.out.println("🗑️ Sensor eliminado: " + idSensor);
    }
    
    /**
     * Obtiene todos los sensores registrados
     * @return Lista con todos los sensores
     */
    public List<Sensor> obtenerTodosSensores() {
        return new ArrayList<>(sensores.values());
    }
    
    /**
     * Obtiene sensores filtrados por tipo
     * @param tipo Tipo de sensor a filtrar
     * @return Lista de sensores del tipo especificado
     */
    public List<Sensor> obtenerSensoresPorTipo(String tipo) {
        List<Sensor> sensoresFiltrados = new ArrayList<>();
        for (Sensor sensor : sensores.values()) {
            if (sensor.getTipo().equals(tipo)) {
                sensoresFiltrados.add(sensor);
            }
        }
        return sensoresFiltrados;
    }
    
    /**
     * Obtiene estadísticas generales del sistema
     * @return Mapa con estadísticas del sistema
     */
    public Map<String, Object> obtenerEstadisticas() {
        Map<String, Object> estadisticas = new HashMap<>();
        estadisticas.put("totalSensores", sensores.size());
        
        // Contar sensores por tipo
        Map<String, Integer> sensoresPorTipo = new HashMap<>();
        for (Sensor sensor : sensores.values()) {
            sensoresPorTipo.put(sensor.getTipo(), 
                sensoresPorTipo.getOrDefault(sensor.getTipo(), 0) + 1);
        }
        estadisticas.put("sensoresPorTipo", sensoresPorTipo);
        
        // Calcular valores promedio por tipo
        Map<String, Double> valoresPromedio = new HashMap<>();
        for (String tipo : sensoresPorTipo.keySet()) {
            List<Sensor> sensoresTipo = obtenerSensoresPorTipo(tipo);
            double promedio = sensoresTipo.stream()
                .mapToDouble(Sensor::getValor)
                .average()
                .orElse(0.0);
            valoresPromedio.put(tipo, promedio);
        }
        estadisticas.put("valoresPromedio", valoresPromedio);
        
        return estadisticas;
    }
    
    /**
     * Obtiene la cantidad total de sensores registrados
     * @return Número de sensores registrados
     */
    public int obtenerCantidadSensores() {
        return sensores.size();
    }
}
