package com.mensagens.mensagem;

import java.time.LocalDateTime;

public class Mensagem {
    private int id;
    private String conteudo;
    private int remetenteId;
    private int destinatarioId;
    private LocalDateTime dataEnvio;

    public Mensagem(String conteudo, int remetenteId, int destinatarioId) {
        this.conteudo = conteudo;
        this.remetenteId = remetenteId;
        this.destinatarioId = destinatarioId;
        this.dataEnvio = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public int getRemetenteId() {
        return remetenteId;
    }

    public void setRemetenteId(int remetenteId) {
        this.remetenteId = remetenteId;
    }

    public int getDestinatarioId() {
        return destinatarioId;
    }

    public void setDestinatarioId(int destinatarioId) {
        this.destinatarioId = destinatarioId;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
}
