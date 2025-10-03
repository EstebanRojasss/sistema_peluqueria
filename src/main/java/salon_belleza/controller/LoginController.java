package salon_belleza.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import net.synedra.validatorfx.Validator;
import salon_belleza.infraestructure.services.AuthServiceImpl;

import java.util.Objects;

public class LoginController {


    private final AuthServiceImpl authService;
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;


    public LoginController() {
        this.authService = AuthServiceImpl.getInstance();
    }

    @FXML
    private void initialize() {

        txtPassword.setOnAction(e -> iniciarSesion());

    }

    @FXML
    private void iniciarSesion() {
        try {
            String usuario = txtUsuario.getText().trim();
            String password = txtPassword.getText();

            validatorUsuario(usuario);
            validatorContrasenha(password);
            if (authService.checkPasswd(usuario, password)) {
                abrirSistemaPrincipal();
            }

        } catch (Exception e) {
            System.out.println("Metodo iniciar sesion---- " + e.getMessage());
        }

    }

    @FXML
    private void recuperarPassword() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Recuperar Contraseña");
        alert.setHeaderText("Contacte al administrador");
        alert.setContentText("Para recuperar su contraseña, contacte al administrador del sistema.");
        alert.showAndWait();
    }

    private void abrirSistemaPrincipal() {
        try {
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().
                    getResource("/main-system.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Sistema de Gestión");
            stage.setMaximized(true);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void validatorUsuario(String usuario) {
        Validator validator = new Validator();
        validator.createCheck()
                .dependsOn(usuario, txtUsuario.textProperty())
                .withMethod(u -> {
                    String nombreUsuario = u.get("usuario");
                    if (nombreUsuario == null || usuario.trim().isEmpty()) {
                        u.error("El nombre de usuario no puede estar vacío.");
                    }
                });
    }

    private void validatorContrasenha(String contrasenha) {
        Validator validator = new Validator();
        validator.createCheck()
                .dependsOn(contrasenha, txtPassword.textProperty())
                .withMethod(p -> {
                    String passwd = p.get("contrasenha");
                    if (passwd == null || passwd.trim().isEmpty()) {
                        p.error("Debe ingresar la contraseña.");
                    }
                });
    }

    private void cargarUsuarioRecordado() {
    }

    private void guardarUsuarioRecordado(String usuario) {
    }
}
