package com.mensagens.mensagem;

import java.time.LocalDateTime;
import java.util.List;

public class MensagemService {
    private final MensagemCRUD mensagemCRUD;

    public MensagemService(MensagemCRUD mensagemCRUD) {
        this.mensagemCRUD = mensagemCRUD;
    }

    public boolean enviarMensagem(String conteudo, int remetenteId, int destinatarioId) {
        if (conteudo == null || conteudo.trim().isEmpty()) {
            System.err.println("Mensagem não pode ser vazia.");
            return false;
        }
        if (remetenteId == destinatarioId) {
            System.err.println("O remetente e o destinatário não podem ser iguais.");
            return false;
        }
        Mensagem mensagem = new Mensagem(conteudo, remetenteId, destinatarioId, LocalDateTime.now());
        return mensagemCRUD.addMensagem(mensagem);
    }

    public List<Mensagem> listarMensagensPorDestinatario(int destinatarioId) {
        return mensagemCRUD.getMensagensByDestinatario(destinatarioId);
    }

    public boolean excluirMensagem(int id) {
        return mensagemCRUD.deleteMensagem(id);
    }
}
