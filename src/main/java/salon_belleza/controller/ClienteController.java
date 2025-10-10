package salon_belleza.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import net.synedra.validatorfx.Validator;
import salon_belleza.domain.entities.Cliente;
import salon_belleza.infraestructure.daos.ClienteDaoImpl;
import salon_belleza.utils.ValidatorBuilder;

import java.time.LocalDate;

public class ClienteController extends BaseController{

    private ClienteDaoImpl clienteDao;
    private ObservableList<Cliente> clientesObs;
    private FilteredList<Cliente> clientesFiltered;

    public void initClienteControllerDpendencies(ClienteDaoImpl clienteDao) {
        this.clienteDao = clienteDao;
        mostrarListaClientesAlIniciar();
    }


    @FXML
    private TextField txtDniAddCliente;
    @FXML
    private TextField txtBuscar;
    @FXML
    private ComboBox cbFiltroEstado;
    @FXML
    private Label lblContador;
    @FXML
    private Label lblPagina;
    @FXML
    private TextField txtNombreAddCliente;
    @FXML
    private TextField txtTelefonoAddCliente;
    @FXML
    private TableView<Cliente> tblClientes;
    @FXML
    private TableColumn<Cliente, String> colId;
    @FXML
    private TableColumn<Cliente, String> colNombre;
    @FXML
    private TableColumn<Cliente, String> colTelefono;
    @FXML
    private TableColumn<Cliente, LocalDate> colFechaRegistro;
    @FXML
    private TableColumn<Cliente, String> colDni;
    @FXML
    private VBox panelAddCliente;

    @FXML
    private void nuevoCliente() {
        panelAddCliente.setVisible(!panelAddCliente.isVisible());
    }

    @FXML
    private void guardarCliente() {
        if(!validator.containsErrors()){
            Cliente cliente = buildCliente();
            clienteDao.saveClient(cliente);
            clientesObs.add(cliente);
            tblClientes.refresh();
        }
    }

    @FXML
    private void generarReporte(ActionEvent actionEvent) {
    }

    @FXML
    private void buscarClientes() {

    }

    @FXML
    private void limpiarFiltros() {
        txtBuscar.setText("");
    }

    @FXML
    private void paginaAnterior(ActionEvent actionEvent) {
    }

    @FXML
    private void paginaSiguiente(ActionEvent actionEvent) {
    }

    @FXML
    private void cerrarDetalle() {
        panelAddCliente.setVisible(!panelAddCliente.isVisible());
    }

    @FXML
    private void eliminarCliente() {
        Cliente cliente = tblClientes.getSelectionModel().getSelectedItem();
        if (cliente != null && !validator.containsErrors()) {
            clienteDao.deleteClienteByName(cliente);
            clientesObs.remove(cliente);
        }
    }

    @FXML
    private void actualizarCliente() {
        Cliente cliente = tblClientes.getSelectionModel().getSelectedItem();
        if (cliente != null && !validator.containsErrors()) {
            cliente.setNombre(txtNombreAddCliente.getText());
            cliente.setTelefono(txtTelefonoAddCliente.getText());
            cliente.setDni(txtDniAddCliente.getText());

            clienteDao.updateClienteByID(cliente);
            tblClientes.refresh();
        }
    }

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colFechaRegistro.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));
        colDni.setCellValueFactory(new PropertyValueFactory<>("dni"));

        clientesObs = FXCollections.observableArrayList();
        clientesFiltered = new FilteredList<>(clientesObs, p -> true);

        tblClientes.setItems(clientesFiltered);

        txtBuscar.textProperty().addListener((obs, oldValue, newValue) -> {
            clientesFiltered.setPredicate(cliente -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String filtro = newValue.toLowerCase();
                return cliente.getNombre().toLowerCase().contains(filtro)
                        || cliente.getTelefono().toLowerCase().contains(filtro)
                        || cliente.getDni().toLowerCase().contains(filtro);
            });
        });

        tblClientes.setOnMousePressed(mouseEvent -> seleccionarCliente());
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

    private void mostrarListaClientesAlIniciar() {
        clientesObs.setAll(clienteDao.findAllClients());
    }

    private void seleccionarCliente() {
        limpiarCamposDetalleCliente();
        Cliente cliente = tblClientes.getSelectionModel().getSelectedItem();
        if (txtNombreAddCliente.getText().isEmpty()
                && txtTelefonoAddCliente.getText().isEmpty()
                && txtDniAddCliente.getText().isEmpty()) {
            txtNombreAddCliente.setText(cliente.getNombre());
            txtTelefonoAddCliente.setText(cliente.getTelefono());
            txtDniAddCliente.setText(cliente.getDni());
        }
    }


    private void limpiarCamposDetalleCliente() {
        txtNombreAddCliente.setText("");
        txtTelefonoAddCliente.setText("");
        txtDniAddCliente.setText("");
    }

    @Override
    protected void setUpValidations(Validator validator) {
        ValidatorBuilder.create(validator)
                .field("nombreCliente", txtNombreAddCliente.textProperty())
                .decorates(txtNombreAddCliente)
                .validateNoEmpty("Debe ingresar el nombre.")
                .build();

        ValidatorBuilder.create(validator)
                .field("dni", txtDniAddCliente.textProperty())
                .decorates(txtDniAddCliente)
                .validateTooLongProperty("El numero de cedula debe tener 10 digitos")
                .build();
    }
}
