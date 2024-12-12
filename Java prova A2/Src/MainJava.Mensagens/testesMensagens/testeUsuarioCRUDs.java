package com.mensagens.usuario;

import org.junit.jupiter.api.*;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioCRUDTest {

    private Connection connection;
    private UsuarioCRUD usuarioCRUD;

    @BeforeAll
    void setupDatabase() throws SQLException {
        // Conectando ao banco de dados de teste (em memória)
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        Statement stmt = connection.createStatement();
        
        // Criando tabelas de teste
        stmt.execute("CREATE TABLE usuarios (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "email VARCHAR(255) UNIQUE NOT NULL, " +
                "senha VARCHAR(255) NOT NULL, " +
                "profissao VARCHAR(255))");

        usuarioCRUD = new UsuarioCRUD(connection);
    }

    @AfterAll
    void teardownDatabase() throws SQLException {
        connection.close();
    }

    @Test
    void testAdicionarUsuario() throws SQLException {
        Usuario usuario = new Usuario(0, "persona@exemplo.com", "12345", "Desenvolvedor");
        boolean result = usuarioCRUD.addUsuario(usuario);

        assertTrue(result, "O usuário deveria ser adicionado com sucesso.");

        // Verifica se o usuário foi adicionado no banco
        Usuario usuarioEncontrado = usuarioCRUD.findUsuarioByEmail("persona@exemplo.com");
        assertNotNull(usuarioEncontrado, "O usuário deveria ser encontrado.");
        assertEquals("teste@exemplo.com", usuarioEncontrado.getEmail());
    }

    @Test
    void testFindUsuarioPorEmail() throws SQLException {
        Usuario usuario = new Usuario(0, "persona2@exemplo.com", "senha123", "Tester");
        usuarioCRUD.addUsuario(usuario);

        Usuario usuarioEncontrado = usuarioCRUD.findUsuarioByEmail("persona2@exemplo.com");
        assertNotNull(usuarioEncontrado, "O usuário deveria ser encontrado.");
        assertEquals("persona2@exemplo.com", usuarioEncontrado.getEmail());
        assertEquals("Tester", usuarioEncontrado.getProfissao());
    }

    @Test
    void testExcluirUsuario() throws SQLException {
        Usuario usuario = new Usuario(0, "persona3@exemplo.com", "senha123", "Gerente");
        usuarioCRUD.addUsuario(usuario);

        Usuario usuarioEncontrado = usuarioCRUD.findUsuarioByEmail("persona3@exemplo.com");
        assertNotNull(usuarioEncontrado, "O usuário deveria ser encontrado antes de ser excluído.");

        boolean excluido = usuarioCRUD.deleteUsuario(usuarioEncontrado.getId());
        assertTrue(excluido, "O usuário deveria ser excluído com sucesso.");

        Usuario usuarioNaoEncontrado = usuarioCRUD.findUsuarioByEmail("persona3@exemplo.com");
        assertNull(usuarioNaoEncontrado, "O usuário não deveria ser encontrado após a exclusão.");
    }
}
