package salon_belleza.domain.entities;

import java.time.LocalDate;
import java.util.UUID;

public class Cliente {

    private final String id;
    private String nombre;
    private String telefono;
    private LocalDate fecha_regsitro;
    private String dni;



    public Cliente(String nombre, String telefono,LocalDate fecha_registro,String dni){
        UUID uuid = UUID.randomUUID();
        this.id = uuid.toString();
        this.telefono = telefono;
        this.fecha_regsitro = fecha_registro;
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
