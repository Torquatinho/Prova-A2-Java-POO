import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioServiceTest {
    private Connection connection;
    private UsuarioService usuarioService;

    @BeforeEach
    void setup() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        UsuarioCRUD usuarioCRUD = new UsuarioCRUD(connection);
        usuarioService = new UsuarioService(usuarioCRUD);
        connection.createStatement().execute("CREATE TABLE usuarios (id INT AUTO_INCREMENT PRIMARY KEY, email VARCHAR(255), senha VARCHAR(255), profissao VARCHAR(255));");
    }

    @Test
    void testRegisterUser() {
        assertTrue(usuarioService.registerUser("test@test.com", "senha123", "Desenvolvedor"));
    }

    @Test
    void testAuthenticate() {
        usuarioService.registerUser("test@test.com", "senha123", "Desenvolvedor");

        Usuario usuario = usuarioService.authenticate("test@test.com", "senha123");
        assertNotNull(usuario);
        assertEquals("test@test.com", usuario.getEmail());
    }

    @Test
    void testDeleteUser() {
        usuarioService.registerUser("test@test.com", "senha123", "Desenvolvedor");

        assertTrue(usuarioService.deleteUser(1));
    }
}