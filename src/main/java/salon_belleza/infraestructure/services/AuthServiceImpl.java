package salon_belleza.infraestructure.services;

import org.mindrot.jbcrypt.BCrypt;
import salon_belleza.domain.service.AuthService;
import salon_belleza.infraestructure.daos.UsuarioDaoImpl;

public class AuthServiceImpl implements AuthService {

    private final UsuarioDaoImpl usuarioDao;
    private static AuthServiceImpl INSTANCE;

    private AuthServiceImpl(UsuarioDaoImpl usuarioDao){
        this.usuarioDao = usuarioDao;
    }

    public static void init(UsuarioDaoImpl usuarioDao){
        if(INSTANCE == null){
            INSTANCE = new AuthServiceImpl(usuarioDao);
        }
    }

    public static AuthServiceImpl getInstance(){
        if(INSTANCE == null){
            throw new IllegalStateException("AuthService no se ha inicializado");
        }

        return INSTANCE;
    }



    @Override
    public String encryptPasswd(String passwd) {
        return BCrypt.hashpw(passwd, BCrypt.gensalt());
    }

    @Override
    public boolean checkPasswd(String nombre, String contrasenha) {
        String senhaAComparar = usuarioDao.findByName(nombre).get(0).getContrasenha();
        return BCrypt.checkpw(contrasenha, senhaAComparar);
    }

}
