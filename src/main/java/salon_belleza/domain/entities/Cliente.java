package salon_belleza.domain.entities;

import java.time.LocalDate;
import java.util.UUID;

public class Cliente {

    private final String id;
    private String nombre;
    private String telefono;
    private String dni;
    private LocalDate fecha_regsitro;



    public Cliente(String nombre, String telefono, String dni, LocalDate fecha_registro){
        UUID uuid = UUID.randomUUID();
        this.id = uuid.toString();
        this.telefono = telefono;
        this.dni = dni;
        this.fecha_regsitro = fecha_registro;
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

    public void setFecha_regsitro(LocalDate fecha_regsitro) {
        this.fecha_regsitro = fecha_regsitro;
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

    public LocalDate getFecha_regsitro() {
        return fecha_regsitro;
    }

    public String getId(){
        return id;
    }
}
