package com.example.chatsockets;

public class ChatUsuario {
    private String username;
    private String mensagem;

    public ChatUsuario(String username, String mensagem) {
        this.username = username;
        this.mensagem = mensagem;
    }

    public String getUsername() {
        return username;
    }

    public String getMensagem() {
        return mensagem;
    }
}
