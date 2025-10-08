package salon_belleza.controller;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import net.synedra.validatorfx.Validator;
import salon_belleza.infraestructure.services.AuthServiceImpl;
import salon_belleza.utils.ValidatorBuilder;

public class LoginController extends BaseController {


    private final AuthServiceImpl authService;

    public LoginController() {
        super();
        this.authService = AuthServiceImpl.getInstance();
    }

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;

    @FXML
    private void iniciarSesion() {
        try {
            StringProperty usuario = txtUsuario.textProperty();
            StringProperty password = txtPassword.textProperty();
            if (validator.validate()) {
                if (authService.checkPasswd(usuario.get(), password.get())) {
                    abrirSistemaPrincipal();
                }
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error al iniciar sesion " + e.getMessage());
        }

    }


    private void abrirSistemaPrincipal() {
        try {
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main-system.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Sistema de Gestión");
            stage.setMaximized(true);
            stage.show();

            MainSystemController mainController = loader.getController();
            mainController.initDependencias();
        } catch (Exception e) {
            System.out.println("Ocurrio un error al cargar el modulo de sistema principal " + e.getCause() + "\n" + e.getMessage());
        }
    }

    @Override
    protected void setUpValidations(Validator validator) {
        ValidatorBuilder.create(validator)
                .field("usuario", txtUsuario.textProperty())
                .decorates(txtUsuario)
                .validateNoEmpty("Debe ingresar un nombre de usuario.")
                .build();

        ValidatorBuilder.create(validator)
                .field("password", txtPassword.textProperty())
                .decorates(txtPassword)
                .validateNoEmpty("Debe ingresar la contraseña")
                .build();
    }


}
