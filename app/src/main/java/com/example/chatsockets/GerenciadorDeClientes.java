package com.example.chatsockets;

import java.net.Socket;

public class GerenciadorDeClientes{
    private Socket cliente;

    public GerenciadorDeClientes(Socket cliente){
        this.cliente=cliente;
    }
}
