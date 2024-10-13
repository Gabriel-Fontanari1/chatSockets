package com.example.chatsockets;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GerenciadorDeClientes extends Thread {
    private Socket cliente;
    private String nomeCliente;
    static int clientesConectados = 0;
    static final Object lock = new Object();

    public GerenciadorDeClientes(Socket cliente, Context context) {
        this.cliente = cliente;
        start();
    }

    @Override
    public void run() {
        try {
            BufferedReader leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter escritor = new PrintWriter(cliente.getOutputStream(), true);

            String msg = leitor.readLine();
            this.nomeCliente = msg;
            System.out.println("Cliente conectado: " + this.nomeCliente);

            synchronized (lock) {
                clientesConectados++;
                System.out.println("Clientes conectados: " + clientesConectados);
                if (clientesConectados == 2) {
                    escritor.println("Ambos os clientes conectados");
                    System.out.println("Ambos os clientes estão conectados.");
                }
            }

            while (true) {
                msg = leitor.readLine();
                if (msg != null) {
                    escritor.println(this.nomeCliente + ": " + msg);
                }
            }

        } catch (IOException e) {
            System.err.println("O cliente fechou a conexão");
            e.printStackTrace();
        }
    }

}
