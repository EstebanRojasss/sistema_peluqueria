package salon_belleza.domain.entities;

import java.util.UUID;

public class Usuario {

    private final String id;
    private String nombre;
    private String contrasenha;

    private Usuario(String id, String nombre, String contrasenha){
        this.id = id;
        this.nombre = nombre;
        this.contrasenha = contrasenha;
    }

    public static Usuario crearNuevoUsuario(String nombre, String contrasenha){
        return new Usuario(UUID.randomUUID().toString(),nombre, contrasenha);
    }

    public static Usuario desdeBD(String id, String nombre, String contrasenha){
        return new Usuario(id, nombre, contrasenha);
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
