package com.example.chatsockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatUsuario {
    private String username;
    private String mensagem;
    private String ip;

    public ChatUsuario (String username, String mensagem, String ip) {
        this.username = username;
        this.mensagem = mensagem;
        this.ip = ip;
    }

    public String getUsername() {
        return username;
    }

    public String getMensagem() {
        return mensagem;
    }

    public static String clienteSocket(final String username, final String mensagem){
        try {
            final Socket cliente = new Socket("192.168.0.3", 54321);
            new Thread(){
                @Override
                public void run() {
                    try {
                        BufferedReader leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                        PrintWriter escritor = new PrintWriter(cliente.getOutputStream(), true);
                        escritor.println(username);
                        System.out.println("Nome do cliente enviado: " + username);
                        escritor.println(mensagem);
                        System.out.println("Mensagem enviada: " + mensagem);
                        while (true) {
                            String resposta = leitor.readLine();
                            if (resposta != null) {
                                System.out.println("Mensagem do servidor: " + resposta);
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }.start();
        } catch (UnknownHostException e) {
            return "Endereço inválido";
        } catch (IOException e) {
            return "O servidor pode estar fora do ar...";
        }
        return null;
    }

}