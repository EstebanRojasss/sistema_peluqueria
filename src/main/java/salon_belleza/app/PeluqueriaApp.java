package salon_belleza.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import salon_belleza.config.DatabasePool;
import salon_belleza.infraestructure.daos.UsuarioDaoImpl;
import salon_belleza.infraestructure.services.AuthServiceImpl;

import javax.sql.DataSource;

public class PeluqueriaApp extends Application {

    DataSource dataSource = DatabasePool.getDatasource();

    UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(dataSource);

    @Override
    public void start(Stage stage) throws Exception {
        AuthServiceImpl.init(usuarioDao);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    PeluqueriaApp.class.getResource("/login-view.fxml")
            );

            Scene scene = new Scene(fxmlLoader.load());
            stage.setMaximized(true);
            stage.setTitle("Lucy Salón de Belleza");
            stage.setScene(scene);
            stage.show();
        } catch (RuntimeException e) {
            System.out.println("Error al cargar la vista del controlador login " + e.getMessage());
        }

    }

    public static void main(String[] args) {
        launch();
    }
}
