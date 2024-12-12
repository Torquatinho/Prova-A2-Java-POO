import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MensagemServiceTest {
    private Connection connection;
    private MensagemService mensagemService;

    @BeforeEach
    void setup() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        MensagemCRUD mensagemCRUD = new MensagemCRUD(connection);
        mensagemService = new MensagemService(mensagemCRUD);
        connection.createStatement().execute("CREATE TABLE mensagens (id INT AUTO_INCREMENT PRIMARY KEY, conteudo VARCHAR(255), remetente INT, destinatario INT, data_envio TIMESTAMP);");
    }

    @Test
    void testSendMensagem() {
        Mensagem mensagem = new Mensagem("Olá!", 1, 2);
        assertTrue(mensagemService.sendMensagem("Olá!", 1, 2));
    }

    @Test
    void testGetMensagensByUsuario() {
        mensagemService.sendMensagem("Olá!", 1, 2);

        List<Mensagem> mensagens = mensagemService.getMensagensByUsuario(2);
        assertEquals(1, mensagens.size());
        assertEquals("Olá!", mensagens.get(0).getConteudo());
    }

    @Test
    void testDeleteMensagem() {
        mensagemService.sendMensagem("Olá!", 1, 2);

        assertTrue(mensagemService.deleteMensagem(1));
    }
}
