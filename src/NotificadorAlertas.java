import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Clase que implementa el patrón Observer como Sujeto y utiliza el patrón Strategy
 * para el análisis de datos. Gestiona la notificación de alertas a múltiples observadores
 * y permite cambiar dinámicamente la estrategia de análisis.
 */
public class NotificadorAlertas implements SujetoAlerta {
    private static NotificadorAlertas instancia;
    private List<ObservadorAlerta> observadores;
    private EstrategiaAnalisis estrategia;
    
    /**
     * Constructor privado para implementar el patrón Singleton
     * Inicializa la lista de observadores como CopyOnWriteArrayList para thread-safety
     */
    private NotificadorAlertas() {
        this.observadores = new CopyOnWriteArrayList<>();
        this.estrategia = new EstrategiaAnalisisBasica(); // estrategia por defecto
        System.out.println("🔔 Notificador de Alertas inicializado");
    }
    
    /**
     * Método estático para obtener la instancia única del NotificadorAlertas
     * Implementa double-checked locking para thread-safety
     * @return La instancia única del NotificadorAlertas
     */
    public static NotificadorAlertas obtenerInstancia() {
        if (instancia == null) {
            synchronized (NotificadorAlertas.class) {
                if (instancia == null) {
                    instancia = new NotificadorAlertas();
                }
            }
        }
        return instancia;
    }
    
    /**
     * Verifica un sensor utilizando la estrategia actual y notifica si hay alerta
     * @param sensor El sensor a verificar
     */
    public void verificarYNotificar(Sensor sensor) {
        Alerta alerta = estrategia.analizar(sensor);
        if (alerta != null) {
            notificarObservadores(alerta);
        }
    }
    
    @Override
    public void registrarObservador(ObservadorAlerta observador) {
        // Evitar duplicados verificando si ya existe
        if (!observadores.contains(observador)) {
            observadores.add(observador);
            System.out.println("👀 Observador registrado: " + observador.obtenerTipoObservador());
        } else {
            System.out.println("⚠️ Observador ya registrado: " + observador.obtenerTipoObservador());
        }
    }
    
    @Override
    public void eliminarObservador(ObservadorAlerta observador) {
        if (observadores.remove(observador)) {
            System.out.println("👋 Observador eliminado: " + observador.obtenerTipoObservador());
        } else {
            System.out.println("❌ Observador no encontrado: " + observador.obtenerTipoObservador());
        }
    }
    
    @Override
    public void notificarObservadores(Alerta alerta) {
        System.out.println("\n🚨 Notificando " + observadores.size() + " observadores...");
        for (ObservadorAlerta observador : observadores) {
            try {
                observador.actualizar(alerta);
            } catch (Exception e) {
                System.err.println("❌ Error notificando observador " + 
                    observador.obtenerTipoObservador() + ": " + e.getMessage());
            }
        }
    }
    
    /**
     * Establece una nueva estrategia de análisis
     * @param estrategia La nueva estrategia a utilizar
     */
    public void establecerEstrategiaAnalisis(EstrategiaAnalisis estrategia) {
        this.estrategia = estrategia;
        System.out.println("🔄 Estrategia de análisis cambiada: " + estrategia.getClass().getSimpleName());
    }
    
    /**
     * Obtiene la cantidad de observadores registrados
     * @return Número de observadores registrados
     */
    public int obtenerCantidadObservadores() {
        return observadores.size();
    }
    
    /**
     * Obtiene la lista de observadores registrados
     * @return Lista de observadores
     */
    public List<ObservadorAlerta> obtenerObservadores() {
        return new ArrayList<>(observadores);
    }
    
    /**
     * Obtiene la estrategia de análisis actual
     * @return La estrategia de análisis actual
     */
    public EstrategiaAnalisis obtenerEstrategiaActual() {
        return estrategia;
    }
}
