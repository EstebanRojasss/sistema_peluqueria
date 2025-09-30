package salon_belleza.domain.dao;

import salon_belleza.domain.entities.Usuario;

import java.util.List;
import java.util.Set;

public interface UsuarioDao {

    void save(Usuario usuario);

    Set<Usuario> findAllAdmins();

    Set<Usuario>findAllEmployeers();

    Usuario findById(String id);

    void update(Usuario usuario);

    void delete(Usuario usuario);

    List<Usuario> findByName(String name);

    Usuario findAdmin(String rol);
}
