package com.mensagens.usuario;

import com.mensagens.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioCRUD {
    public boolean addUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (email, senha, profissao) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getProfissao());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar usuário: " + e.getMessage());
            return false;
        }
    }

    public Usuario getUsuarioById(int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("profissao")
                );
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
        }

        return null;
    }

}
