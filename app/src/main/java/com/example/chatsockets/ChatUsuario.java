package com.example.chatsockets;

import android.util.Log;

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

    public static void clienteSocket(final String username, final String mensagem) {
        new Thread(() -> {
            try {
                Log.d("Cliente", "Tentando conectar ao servidor");
                final Socket cliente = new Socket("10.0.2.2", 54321); // Use 10.0.2.2 para o emulador
                Log.d("Cliente", "Conectado ao servidor com sucesso");

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
            } catch (UnknownHostException e) {
                Log.e("Cliente", "Endereço do servidor inválido: " + e.getMessage());
            } catch (IOException e) {
                Log.e("Cliente", "Erro ao conectar ou comunicar com o servidor: " + e.getMessage());
            }
        }).start(); // Inicia a nova thread
    }

}