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

    public static String clienteSocket(){
        try{
            final Socket cliente = new Socket("127.0.0.1", 54321);
            new Thread(){
                @Override
                public void run() {
                    try {
                        BufferedReader leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                        PrintWriter escritor = new PrintWriter(cliente.getOutputStream());

                        while(true){
                            String mensagem = leitor.readLine();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }.start();

        }catch (UnknownHostException e){
            return "indereço invalido";
        }catch (IOException e){
            return "O servidor pode estar fora do ar...";
        }
        return null;
    }
}