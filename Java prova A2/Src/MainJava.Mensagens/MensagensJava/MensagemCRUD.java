package com.mensagens.mensagem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MensagemCRUD {
    private final Connection connection;

    public MensagemCRUD(Connection connection) {
        this.connection = connection;
    }

    // Adicionar mensagem
    public boolean addMensagem(Mensagem mensagem) {
        String sql = "INSERT INTO mensagens (conteudo, remetente_id, destinatario_id, data_envio) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, mensagem.getConteudo());
            stmt.setInt(2, mensagem.getRemetenteId());
            stmt.setInt(3, mensagem.getDestinatarioId());
            stmt.setTimestamp(4, Timestamp.valueOf(mensagem.getDataEnvio()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar mensagem: " + e.getMessage());
            return false;
        }
    }

    // Obter todas as mensagens
    public List<Mensagem> getAllMensagens() {
        List<Mensagem> mensagens = new ArrayList<>();
        String sql = "SELECT * FROM mensagens";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Mensagem mensagem = new Mensagem(
                        rs.getString("conteudo"),
                        rs.getInt("remetente_id"),
                        rs.getInt("destinatario_id")
                );
                mensagem.setId(rs.getInt("id"));
                mensagem.setDataEnvio(rs.getTimestamp("data_envio").toLocalDateTime());
                mensagens.add(mensagem);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar mensagens: " + e.getMessage());
        }
        return mensagens;
    }

    // Buscar mensagens destinadas a um usuário
    public List<Mensagem> getMensagensPorDestinatario(int destinatarioId) {
        List<Mensagem> mensagens = new ArrayList<>();
        String sql = "SELECT * FROM mensagens WHERE destinatario_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, destinatarioId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Mensagem mensagem = new Mensagem(
                            rs.getString("conteudo"),
                            rs.getInt("remetente_id"),
                            rs.getInt("destinatario_id")
                    );
                    mensagem.setId(rs.getInt("id"));
                    mensagem.setDataEnvio(rs.getTimestamp("data_envio").toLocalDateTime());
                    mensagens.add(mensagem);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar mensagens por destinatário: " + e.getMessage());
        }
        return mensagens;
    }
}
