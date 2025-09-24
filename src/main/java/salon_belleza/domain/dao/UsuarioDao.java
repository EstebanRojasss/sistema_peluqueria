package salon_belleza.domain.dao;

import salon_belleza.domain.entities.Usuario;

import java.util.List;

public interface UsuarioDao {

    void save(Usuario usuario);

    Set<Usuario> findAllAdmins();

    Set<Usuario>findAllEmployeers();

    Usuario findById();

    void update(Usuario usuario);

    void delete(Usuario usuario);

    Usuario findByName();
}
