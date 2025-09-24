import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import salon_belleza.config.DatabasePool;
import salon_belleza.domain.entities.Usuario;
import salon_belleza.infraestructure.daos.UsuarioDaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioDaoTest {
    private HikariDataSource dataSource;
    private Connection connection;
    private UsuarioDaoImpl usuarioDAO;

    @BeforeEach
    void setUp() throws SQLException {
        dataSource = DatabasePool.getDatasource();
        connection = dataSource.getConnection();
        connection.setAutoCommit(false);
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
    void testSaveUsuario() throws SQLException {
        // Arrange
        Usuario usuario = new Usuario("TestUser", "password123");

        // Act
        usuarioDAO.save(usuario);

        String expected = "TestUser";

        assertUsuarioExists(usuario.getId(), expected);
    }

    @Test
    void testFinalAllAdmins() throws SQLException {
        String name = "esteban";
        String userType = "ADMIN";
        assertAdminsExists(userType, name);
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
