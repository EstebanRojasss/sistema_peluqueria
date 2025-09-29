package salon_belleza.domain.service;

public interface LoginService {

    String encryptPasswd(String passwd);

    boolean checkPasswd(String textPlane, String passwd);

}
