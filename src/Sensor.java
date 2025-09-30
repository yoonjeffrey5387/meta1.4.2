import java.util.Date;

/**
 * Clase que representa un sensor IoT en el sistema de monitoreo
 * Contiene información sobre el sensor y sus mediciones
 */
public class Sensor {
    private String id;
    private String tipo; // "temperatura", "vibracion", "energia", "calidad_aire", "humedad"
    private double valor;
    private String ubicacion;
    private Date ultimaActualizacion;
    
    /**
     * Constructor del sensor
     * @param id Identificador único del sensor
     * @param tipo Tipo de sensor (temperatura, vibracion, energia, etc.)
     * @param valor Valor actual medido por el sensor
     * @param ubicacion Ubicación física del sensor en la fábrica
     */
    public Sensor(String id, String tipo, double valor, String ubicacion) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.ubicacion = ubicacion;
        this.ultimaActualizacion = new Date();
    }
    
    // Getters y Setters
    public String getId() { 
        return id; 
    }
    
    public String getTipo() { 
        return tipo; 
    }
    
    public double getValor() { 
        return valor; 
    }
    
    public void setValor(double valor) { 
        this.valor = valor; 
    }
    
    public String getUbicacion() { 
        return ubicacion; 
    }
    
    public Date getUltimaActualizacion() { 
        return ultimaActualizacion; 
    }
    
    public void setUltimaActualizacion(Date fecha) { 
        this.ultimaActualizacion = fecha; 
    }
    
    @Override
    public String toString() {
        return String.format("Sensor{id='%s', tipo='%s', valor=%.2f, ubicacion='%s'}", 
                           id, tipo, valor, ubicacion);
    }
}
