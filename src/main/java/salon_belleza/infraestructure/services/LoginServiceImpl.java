package salon_belleza.infraestructure.services;

import org.mindrot.jbcrypt.BCrypt;
import salon_belleza.domain.service.LoginService;

public class LoginServiceImpl implements LoginService  {

    private static final LoginServiceImpl INSTANCE = new LoginServiceImpl();

    private LoginServiceImpl(){}

    public static LoginServiceImpl getInstance(){
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
