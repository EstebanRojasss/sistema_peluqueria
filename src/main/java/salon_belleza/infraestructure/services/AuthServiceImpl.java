package salon_belleza.infraestructure.services;

import org.mindrot.jbcrypt.BCrypt;
import salon_belleza.domain.service.AuthService;

public class AuthServiceImpl implements AuthService {

    private static final AuthServiceImpl INSTANCE = new AuthServiceImpl();

    private AuthServiceImpl(){}

    public static AuthServiceImpl getInstance(){
        return INSTANCE;
    }



    @Override
    public String encryptPasswd(String passwd) {
        return BCrypt.hashpw(passwd, BCrypt.gensalt());
    }

    @Override
    public boolean checkPasswd(String textPlane, String passwd) {
        return BCrypt.checkpw(textPlane, passwd);
    }

}
