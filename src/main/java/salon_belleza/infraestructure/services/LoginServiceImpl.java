package salon_belleza.infraestructure.services;

import org.mindrot.jbcrypt.BCrypt;
import salon_belleza.domain.service.LoginService;

public class LoginServiceImpl implements LoginService  {

    @Override
    public void encryptPasswd(String passwd) {
        BCrypt.hashpw(passwd, BCrypt.gensalt());
    }

}
