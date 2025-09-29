package salon_belleza.domain.service;

public interface LoginService {

    void encryptPasswd(String passwd);

    boolean checkPasswd(String textPlane, String passwd);

}
