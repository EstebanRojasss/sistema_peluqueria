import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import salon_belleza.config.DatabasePool;
import salon_belleza.domain.entities.Cliente;
import salon_belleza.domain.entities.Usuario;
import salon_belleza.infraestructure.daos.ClienteDaoImpl;
import salon_belleza.infraestructure.daos.UsuarioDaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DaoTest {
    private HikariDataSource dataSource;
    private Connection connection;
    private UsuarioDaoImpl usuarioDAO;
    private ClienteDaoImpl clienteDao;

    @BeforeEach
    void setUp() throws SQLException {
        dataSource = DatabasePool.getDatasource();
        connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        clienteDao = new ClienteDaoImpl(dataSource);
        usuarioDAO = new UsuarioDaoImpl(dataSource);
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (connection != null) {
            connection.rollback();
            connection.setAutoCommit(true);
            connection.close();
        }
    }


    @Test
    void testFinalAllAdmins() throws SQLException {
        String name = "esteban";
        String userType = "ADMIN";
        assertAdminsExists(userType, name);
    }

    @Test
    void testSaveClient() {
        Cliente cliente = new Cliente("esteban","123123", LocalDate.of(2000,10, 10), "123");
        clienteDao.saveClient(cliente);

        assertClientExists(cliente.getId(), cliente.getNombre());
    }


    private void assertClientExists(String id, String expected) {
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(
                "SELECT c.nombre FROM cliente c WHERE c.id_cliente = ?")) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            assertTrue(rs.next());
            assertEquals(expected, rs.getString("nombre"));
        } catch (SQLException e) {
            System.out.println("Ocurrio un error en el test " + e.getMessage());
        }
    }

    @Test
    void testFindClientByname(){
        String nombre = "esteban";
        assertClientByNameExist(nombre);
    }


    private void assertClientByNameExist(String nombre){
        for(Cliente cliente: clienteDao.findClientByName(nombre)){
            System.out.println(cliente);
        }
    }

    @Test
    void testFindClientByDni(){

    }



    @Test
    void testSaveUsuario() throws SQLException {
        Usuario usuario = Usuario.crearNuevoUsuario("TestUser", "password123");
        usuarioDAO.save(usuario);

        String expected = "TestUser";

        assertUsuarioExists(usuario.getId(), expected);
    }

    @Test
    void saveAdmin() throws SQLException{
        String passw = "admin1000";
        String hashPass = BCrypt.hashpw(passw, BCrypt.gensalt());
        Usuario usuario = Usuario.crearNuevoUsuario("admin", hashPass);
        usuarioDAO.save(usuario);


        assertUsuarioExists(usuario.getId(), "admin");
    }


    private void assertAdminsExists(String userType, String expectedAdminName) throws SQLException {
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement("""
                SELECT u.name FROM user u
                    INNER JOIN rol_user ru ON u.id_user = ru.user_id\s
                    INNER JOIN rol r ON r.id_rol = ru.rol_id
                    WHERE r.role_name = ?
                """)) {
            ps.setString(1, userType);
            ResultSet rs = ps.executeQuery();
            assertTrue(rs.next());
            assertEquals(expectedAdminName, rs.getString("name"));
        }
    }

    private void assertUsuarioExists(String id, String expectedName) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT name FROM user WHERE id_user = ?")) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            assertTrue(rs.next());
            assertEquals(expectedName, rs.getString("name"));
        }
    }

}
