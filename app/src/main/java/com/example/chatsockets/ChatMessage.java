package com.example.chatsockets;

public class ChatMessage {
    private String username;
    private String mensagem;

    public ChatMessage (String username, String mensagem) {
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
