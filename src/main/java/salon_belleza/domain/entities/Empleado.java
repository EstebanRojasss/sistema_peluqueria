package salon_belleza.domain.entities;

import java.util.UUID;

public class Empleado {

    private final String id;
    private String nombre;
    private String telefono;


    public Empleado(String nombre, String telefono) {
        UUID uuid = UUID.randomUUID();
        this.id = uuid.toString();
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getId(){
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
