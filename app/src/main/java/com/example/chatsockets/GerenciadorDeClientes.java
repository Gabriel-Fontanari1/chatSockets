package com.example.chatsockets;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;

public class GerenciadorDeClientes extends Thread{
    private Socket cliente;
    private String nomeCliente;

    public GerenciadorDeClientes(Socket cliente){
        this.cliente=cliente;
        start();
    }

    @Override
    public void run() {
        //ta feito com parametros para o terminal, vou modificar para o layout depois
        try {
            //perguntar o nome do usuario
            BufferedReader leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter escritor = new PrintWriter(cliente.getOutputStream(), true);
            String msg = leitor.readLine();
            this.nomeCliente = msg;

            while(true){
                msg = leitor.readLine();
                escritor.println(this.nomeCliente + "" +msg);
            }


        } catch (IOException e) {
            System.err.println("O cliente fechou a conexão");
            e.printStackTrace();
        }
    }
}
