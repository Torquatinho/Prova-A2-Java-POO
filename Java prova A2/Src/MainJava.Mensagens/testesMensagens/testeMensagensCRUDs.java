import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MensagemCRUDTest {
    private Connection connection;
    private MensagemCRUD mensagemCRUD;

    @BeforeEach
    void setup() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        mensagemCRUD = new MensagemCRUD(connection);
        connection.createStatement().execute("CREATE TABLE mensagens (id INT AUTO_INCREMENT PRIMARY KEY, conteudo VARCHAR(255), remetente INT, destinatario INT, data_envio TIMESTAMP);");
    }

    @Test
    void testAddMensagem() {
        Mensagem mensagem = new Mensagem("Olá!", 1, 2);
        assertTrue(mensagemCRUD.addMensagem(mensagem));
    }

    @Test
    void testGetMensagensByDestinatario() {
        Mensagem mensagem = new Mensagem("Olá!", 1, 2);
        mensagemCRUD.addMensagem(mensagem);

        List<Mensagem> mensagens = mensagemCRUD.getMensagensByDestinatario(2);
        assertEquals(1, mensagens.size());
        assertEquals("Olá!", mensagens.get(0).getConteudo());
    }

    @Test
    void testDeleteMensagem() {
        Mensagem mensagem = new Mensagem("Olá!", 1, 2);
        mensagemCRUD.addMensagem(mensagem);

        assertTrue(mensagemCRUD.deleteMensagem(1));
    }
}
