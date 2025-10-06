package salon_belleza.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class ClienteController {

    @FXML
    private TextField txtBuscar;
    @FXML
    private ComboBox cbFiltroEstado;
    @FXML
    private Label lblContador;
    @FXML
    private TableView tblClientes;
    @FXML
    private Label lblPagina;
    @FXML
    private VBox panelDetalle;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtEmail;
    @FXML
    private DatePicker dpFechaNacimiento;
    @FXML
    private TextArea txtNotas;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colTelefono;
    @FXML
    private TableColumn colEmail;
    @FXML
    private TableColumn colFechaRegistro;
    @FXML
    private TableColumn colUltimaVisita;
    @FXML
    private TableColumn colTotalVisitas;
    @FXML
    private TableColumn colEstado;
    @FXML
    private TableColumn colAcciones;

    public void nuevoCliente(ActionEvent actionEvent) {
        
    }

    @FXML
    private void guardarCliente() {
        sava_changes_btn.setOnAction(saveUser -> clienteDao.saveClient(buildCliente()));
    }


    public void generarReporte(ActionEvent actionEvent) {
    }

    public void buscarClientes(ActionEvent actionEvent) {
    }

    public void limpiarFiltros(ActionEvent actionEvent) {
    }

    public void paginaAnterior(ActionEvent actionEvent) {
    }

    public void paginaSiguiente(ActionEvent actionEvent) {
    }

    @FXML
    private void cerrarDetalle() {
        panelAddCliente.setVisible(panelAddCliente.isVisible());
    }


    public void eliminarCliente(ActionEvent actionEvent) {
    }

    public void initialize() {
        clientesObs.setAll(clienteDao.findAllClients());
        tblClientes.setItems(clientesObs);
        
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colFechaRegistro.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));
        colDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
    }


    private void mostrarListaClientes(){

    }

    private Cliente buildCliente() {
        if (txtNombreAddCliente.getText().isEmpty()) {
            throw new IllegalStateException("No se puede dejar el espacio de nombre en blanco");
        }
        String nombre = txtNombreAddCliente.getText();
        String telefono = txtTelefonoAddCliente.getText();
        String dni = txtDniAddCliente.getText();
        return new Cliente(nombre, telefono, LocalDate.now(), dni);
    }

    private void mostrarListaClientesAlIniciar(){
        clientesObs.setAll(clienteDao.findAllClients());
    }
}
