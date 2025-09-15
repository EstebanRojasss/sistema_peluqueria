package salon_belleza.domain.entities;

import java.math.BigDecimal;

public class Servicio {
    private final String id;
    private String nombreServicio;
    private BigDecimal precio;


    public Servicio(String nombre_servicio, BigDecimal precio) {
        this.id =
        this.nombreServicio = nombre_servicio;
        this.precio = precio;
    }


    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public BigDecimal getPrecio() {
        return precio;
    }
}
