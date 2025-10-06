package salon_belleza.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import salon_belleza.config.DatabasePool;
import salon_belleza.infraestructure.daos.ClienteDaoImpl;

import java.util.Objects;

public class MainSystemController {

    public MainSystemController() {
        this.clienteDao = new ClienteDaoImpl(DatabasePool.getDatasource());
    }

    @FXML
    private TableView listClients;
    @FXML
    private TableColumn dni_col;
    @FXML
    private TableColumn nombre_col;
    @FXML
    private TableColumn telefono_col;
    @FXML
    private TableColumn fecha_registro_col;
    @FXML
    private StackPane contenedorModulos;
    @FXML
    private Label lblEstado;

    private ClienteDaoImpl clienteDao;

    @FXML
    public void cargarClientes() {
        String pathViewClientes = "/cliente-view.fxml";
        String cacheKey = "clientes";
        cargarControlador(pathViewClientes, cacheKey);
    }

    public void abrirCaja() {

    }

    public void cerrarCaja(ActionEvent actionEvent) {
    }

    public void salir(ActionEvent actionEvent) {
    }

    public void cargarDashboard(ActionEvent actionEvent) {
    }


    public void cargarCitas(ActionEvent actionEvent) {
    }

    public void cargarServicios(ActionEvent actionEvent) {
    }

    public void cargarInventario(ActionEvent actionEvent) {
    }

    public void cargarVentas(ActionEvent actionEvent) {
    }

    public void cargarEmpleados(ActionEvent actionEvent) {
    }



    private void cargarControlador(String viewPath, String cacheKey){
        if(cacheKey.equals(currentViewId)){
            return;
        }

        Node view;

        if(viewCache.containsKey(cacheKey)){
            view = viewCache.get(cacheKey);
        }else{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(viewPath));

                view = loader.load();


                ClienteController controller = loader.getController();
                controller.initClienteControllerDpendencies(clienteDao);

                viewCache.put(cacheKey,view);

            }catch (IOException e){
                throw new ControllerViewExcepcion("Ocurrió un error al cargar el controlador. \n" + e.getMessage()+"\n"
                        + e.getCause());
            }
        }
        contenedorModulos.getChildren().setAll(view);
        currentViewId = cacheKey;
    }

}


