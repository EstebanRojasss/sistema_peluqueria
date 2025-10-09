package salon_belleza.domain.entities;

import java.time.LocalDate;
import java.util.UUID;

public class Cliente {

    private final String id;
    private String nombre;
    private String telefono;
    private LocalDate fechaRegistro;
    private String dni;



    public Cliente(String nombre, String telefono,LocalDate fecha_registro,String dni){
        UUID uuid = UUID.randomUUID();
        this.nombre = nombre;
        this.id = uuid.toString();
        this.telefono = telefono;
        this.fechaRegistro = fecha_registro;
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDni() {
        return dni;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public String getId(){
        return id;
    }
}
