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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        assertUsuarioExists(usuario.getId(), "TestUser");
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
