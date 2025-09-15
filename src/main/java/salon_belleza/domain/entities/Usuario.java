package salon_belleza.domain.entities;

import java.util.UUID;

public class Usuario {

    private final String id;
    private String nombre;
    private String contrasenha;


    public Usuario( String nombre, String contrasenha) {
        UUID id = UUID.randomUUID();
        this.id = id.toString();
        this.nombre = nombre;
        this.contrasenha = contrasenha;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }
}
