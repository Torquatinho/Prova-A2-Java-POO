package com.Mensagens;

import com.Mensagens.Usuario.*;
import com.Mensagens.Mensagens.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Configuração do banco de dados
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/MensagensDB",
                "root",
                "senha123")) {

            // Inicializando os componentes
            UsuarioCRUD usuarioCRUD = new UsuarioCRUD(connection);
            MensagemCRUD mensagemCRUD = new MensagemCRUD(connection);

            UsuarioService usuarioService = new UsuarioService(usuarioCRUD);
            MensagemService mensagemService = new MensagemService(mensagemCRUD);

            UsuarioController usuarioController = new UsuarioController(usuarioService);
            MensagemController mensagemController = new MensagemController(mensagemService);

            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            System.out.println("Bem-vindo ao sistema de mensagens!");
            while (running) {
                System.out.println("\nEscolha uma opção:");
                System.out.println("1. Registrar usuário");
                System.out.println("2. Fazer login");
                System.out.println("3. Sair");

                int opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1 -> {
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Senha: ");
                        String senha = scanner.nextLine();
                        System.out.print("Profissão: ");
                        String profissao = scanner.nextLine();

                        if (usuarioController.registrarUsuario(email, senha, profissao)) {
                            System.out.println("Usuário registrado com sucesso!");
                        } else {
                            System.out.println("Erro ao registrar usuário.");
                        }
                    }
                    case 2 -> {
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Senha: ");
                        String senha = scanner.nextLine();

                        Usuario usuario = usuarioController.autenticarUsuario(email, senha);
                        if (usuario != null) {
                            System.out.println("Login bem-sucedido! Bem-vindo, " + usuario.getEmail());
                            boolean loggedIn = true;
                            while (loggedIn) {
                                System.out.println("\nOpções:");
                                System.out.println("1. Enviar mensagem");
                                System.out.println("2. Listar mensagens recebidas");
                                System.out.println("3. Fazer logout");

                                int subOpcao = Integer.parseInt(scanner.nextLine());
                                switch (subOpcao) {
                                    case 1 -> {
                                        System.out.print("Destinatário (email): ");
                                        String destinatarioEmail = scanner.nextLine();
                                        System.out.print("Conteúdo: ");
                                        String conteudo = scanner.nextLine();

                                        if (mensagemController.enviarMensagem(usuario, destinatarioEmail, conteudo)) {
                                            System.out.println("Mensagem enviada com sucesso!");
                                        } else {
                                            System.out.println("Erro ao enviar mensagem.");
                                        }
                                    }
                                    case 2 -> {
                                        mensagemController.listarMensagensRecebidas(usuario);
                                    }
                                    case 3 -> loggedIn = false;
                                    default -> System.out.println("Opção inválida.");
                                }
                            }
                        } else {
                            System.out.println("Credenciais inválidas.");
                        }
                    }
                    case 3 -> running = false;
                    default -> System.out.println("Opção inválida.");
                }
            }
            scanner.close();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
