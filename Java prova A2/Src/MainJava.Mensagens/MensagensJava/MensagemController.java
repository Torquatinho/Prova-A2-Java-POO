package com.mensagens.mensagem;

import com.mensagens.usuario.Usuario;

import java.util.List;
import java.util.Scanner;

public class MensagemController {
    private final MensagemService mensagemService;

    public MensagemController(MensagemService mensagemService) {
        this.mensagemService = mensagemService;
    }

    public void menu(Usuario usuarioAtual) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Manutenção de Mensagens ---");
            System.out.println("1. Enviar mensagem");
            System.out.println("2. Listar mensagens recebidas");
            System.out.println("3. Listar mensagens enviadas");
            System.out.println("4. Excluir mensagem");
            System.out.println("0. Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1 -> enviarMensagem(scanner, usuarioAtual);
                case 2 -> listarMensagensRecebidas(usuarioAtual);
                case 3 -> listarMensagensEnviadas(usuarioAtual);
                case 4 -> excluirMensagem(scanner, usuarioAtual);
                case 0 -> {
                    System.out.println("Voltando ao menu anterior...");
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void enviarMensagem(Scanner scanner, Usuario remetente) {
        System.out.print("ID do destinatário: ");
        int destinatarioId = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        System.out.print("Conteúdo da mensagem: ");
        String conteudo = scanner.nextLine();

        if (mensagemService.enviarMensagem(remetente, destinatarioId, conteudo)) {
            System.out.println("Mensagem enviada com sucesso!");
        } else {
            System.out.println("Erro ao enviar a mensagem.");
        }
    }

    private void listarMensagensRecebidas(Usuario destinatario) {
        System.out.println("\n--- Mensagens Recebidas ---");
        List<Mensagem> mensagens = mensagemService.getMensagensRecebidas(destinatario.getId());
        if (mensagens.isEmpty()) {
            System.out.println("Você não possui mensagens recebidas.");
        } else {
            for (Mensagem mensagem : mensagens) {
                System.out.println("De: " + mensagem.getRemetenteId());
                System.out.println("Data de Envio: " + mensagem.getDataEnvio());
                System.out.println("Conteúdo: " + mensagem.getConteudo());
                System.out.println("----");
            }
        }
    }

    private void listarMensagensEnviadas(Usuario remetente) {
        System.out.println("\n--- Mensagens Enviadas ---");
        List<Mensagem> mensagens = mensagemService.getMensagensEnviadas(remetente.getId());
        if (mensagens.isEmpty()) {
            System.out.println("Você não possui mensagens enviadas.");
        } else {
            for (Mensagem mensagem : mensagens) {
                System.out.println("Para: " + mensagem.getDestinatarioId());
                System.out.println("Data de Envio: " + mensagem.getDataEnvio());
                System.out.println("Conteúdo: " + mensagem.getConteudo());
                System.out.println("----");
            }
        }
    }

    private void excluirMensagem(Scanner scanner, Usuario usuarioAtual) {
        System.out.print("ID da mensagem a ser excluída: ");
        int mensagemId = scanner.nextInt();

        if (mensagemService.excluirMensagem(mensagemId, usuarioAtual)) {
            System.out.println("Mensagem excluída com sucesso.");
        } else {
            System.out.println("Erro ao excluir a mensagem ou mensagem não encontrada.");
        }
    }
}
