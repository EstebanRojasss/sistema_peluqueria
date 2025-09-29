package salon_belleza.domain.service;

public interface LoginService {

    void encryptPasswd(String passwd);

    void checkPasswd(String textPlane, String passwd);

}
