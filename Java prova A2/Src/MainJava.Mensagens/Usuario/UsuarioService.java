package com.mensagens.usuario;

import java.util.List;

public class UsuarioService {
    private final UsuarioCRUD usuarioCRUD;

    public UsuarioService(UsuarioCRUD usuarioCRUD) {
        this.usuarioCRUD = usuarioCRUD;
    }

    // Adicionar um novo usuário com validação
    public boolean addUsuario(String email, String senha, String profissao) {
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            System.err.println("E-mail inválido.");
            return false;
        }
        if (senha == null || senha.length() < 6) {
            System.err.println("A senha deve ter pelo menos 6 caracteres.");
            return false;
        }
        if (profissao == null || profissao.trim().isEmpty()) {
            System.err.println("Profissão não pode ser vazia.");
            return false;
        }

        Usuario usuario = new Usuario(email, senha, profissao);
        return usuarioCRUD.addUsuario(usuario);
    }

    // Buscar usuário pelo ID
    public Usuario getUsuarioById(int id) {
        return usuarioCRUD.getUsuarioById(id);
    }

    // Listar todos os usuários
    public List<Usuario> getAllUsuarios() {
        return usuarioCRUD.getAllUsuarios();
    }

    // Atualizar os dados de um usuário
    public boolean updateUsuario(int id, String email, String senha, String profissao) {
        Usuario usuarioExistente = usuarioCRUD.getUsuarioById(id);
        if (usuarioExistente == null) {
            System.err.println("Usuário não encontrado.");
            return false;
        }

        usuarioExistente.setEmail(email);
        usuarioExistente.setSenha(senha);
        usuarioExistente.setProfissao(profissao);

        return usuarioCRUD.updateUsuario(usuarioExistente);
    }

    // Excluir um usuário pelo ID
    public boolean deleteUsuario(int id) {
        Usuario usuario = usuarioCRUD.getUsuarioById(id);
        if (usuario == null) {
            System.err.println("Usuário não encontrado.");
            return false;
        }

        return usuarioCRUD.deleteUsuario(id);
    }

    // Autenticação do usuário
    public Usuario authenticate(String email, String senha) {
        List<Usuario> usuarios = usuarioCRUD.getAllUsuarios();
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        System.err.println("Credenciais inválidas.");
        return null;
    }
}
