package com.example.chatsockets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server extends Thread{
    private static ArrayList<BufferedWriter> clientes;
    private static ServerSocket server;
    private String nome;
    private String con;
    private InputStream in;
    private InputStreamReader inr;
    private BufferedReader bfr;

    public Server(String con) {
        this.con = con;
        try {
            in = con.getInputStream();
            inr = new InputStreamReader(in);
            bfr = new BufferedReader(inr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
