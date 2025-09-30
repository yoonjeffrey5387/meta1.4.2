import java.util.Date;

/**
 * Clase que representa una alerta generada por el sistema IoT
 * Contiene información sobre el sensor que generó la alerta y su nivel de criticidad
 */
public class Alerta {
    private String idSensor;
    private String mensaje;
    private NivelAlerta nivel;
    private Date fechaHora;
    
    /**
     * Constructor de la alerta
     * @param idSensor ID del sensor que generó la alerta
     * @param mensaje Mensaje descriptivo de la alerta
     * @param nivel Nivel de criticidad de la alerta
     * @param fechaHora Fecha y hora cuando se generó la alerta
     */
    public Alerta(String idSensor, String mensaje, NivelAlerta nivel, Date fechaHora) {
        this.idSensor = idSensor;
        this.mensaje = mensaje;
        this.nivel = nivel;
        this.fechaHora = fechaHora;
    }
    
    // Getters
    public String getIdSensor() { 
        return idSensor; 
    }
    
    public String getMensaje() { 
        return mensaje; 
    }
    
    public NivelAlerta getNivel() { 
        return nivel; 
    }
    
    public Date getFechaHora() { 
        return fechaHora; 
    }
    
    @Override
    public String toString() {
        return String.format("Alerta{idSensor='%s', nivel=%s, mensaje='%s', fecha=%s}", 
                           idSensor, nivel, mensaje, fechaHora);
    }
}
