package salon_belleza.domain.service;

public interface AuthService {

    String encryptPasswd(String passwd);

    boolean checkPasswd(String textPlane, String passwd);

}
