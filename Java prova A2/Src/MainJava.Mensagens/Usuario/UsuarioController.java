package com.mensagens.usuario;

import java.util.Scanner;

public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Menu de manutenção de usuários
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Manutenção de Usuários ---");
            System.out.println("1. Cadastrar usuário");
            System.out.println("2. Listar usuários");
            System.out.println("3. Atualizar usuário");
            System.out.println("4. Excluir usuário");
            System.out.println("5. Autenticar usuário (login)");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1 -> cadastrarUsuario(scanner);
                case 2 -> listarUsuarios();
                case 3 -> atualizarUsuario(scanner);
                case 4 -> excluirUsuario(scanner);
                case 5 -> autenticarUsuario(scanner);
                case 0 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void cadastrarUsuario(Scanner scanner) {
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Profissão: ");
        String profissao = scanner.nextLine();

        if (usuarioService.addUsuario(email, senha, profissao)) {
            System.out.println("Usuário cadastrado com sucesso.");
        } else {
            System.out.println("Erro ao cadastrar usuário.");
        }
    }

    private void listarUsuarios() {
        System.out.println("\n--- Lista de Usuários ---");
        for (Usuario usuario : usuarioService.getAllUsuarios()) {
            System.out.println("ID: " + usuario.getId());
            System.out.println("E-mail: " + usuario.getEmail());
            System.out.println("Profissão: " + usuario.getProfissao());
            System.out.println("----");
        }
    }

    private void atualizarUsuario(Scanner scanner) {
        System.out.print("ID do usuário a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        System.out.print("Novo e-mail: ");
        String email = scanner.nextLine();
        System.out.print("Nova senha: ");
        String senha = scanner.nextLine();
        System.out.print("Nova profissão: ");
        String profissao = scanner.nextLine();

        if (usuarioService.updateUsuario(id, email, senha, profissao)) {
            System.out.println("Usuário atualizado com sucesso.");
        } else {
            System.out.println("Erro ao atualizar usuário.");
        }
    }

    private void excluirUsuario(Scanner scanner) {
        System.out.print("ID do usuário a ser excluído: ");
        int id = scanner.nextInt();

        if (usuarioService.deleteUsuario(id)) {
            System.out.println("Usuário excluído com sucesso.");
        } else {
            System.out.println("Erro ao excluir usuário.");
        }
    }

    private void autenticarUsuario(Scanner scanner) {
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Usuario usuario = usuarioService.authenticate(email, senha);
        if (usuario != null) {
            System.out.println("Login bem-sucedido. Bem-vindo, " + usuario.getProfissao() + "!");
        } else {
            System.out.println("Credenciais inválidas.");
        }
    }
}
