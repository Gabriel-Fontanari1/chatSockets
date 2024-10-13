package com.example.chatsockets;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainChat), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        new Thread(() -> {
            ServerSocket servidor = null;
            try {
                runOnUiThread(() -> Toast.makeText(this, "Iniciando o servidor", Toast.LENGTH_SHORT).show());
                servidor = new ServerSocket(54321);
                runOnUiThread(() -> Toast.makeText(this, "Servidor iniciado", Toast.LENGTH_SHORT).show());
                Log.d("Servidor", "Servdor iniciado na porta 54321");

                while (true) {
                    Log.d("Servidor", "Aguardando conexão de clientes...");
                    Socket cliente = servidor.accept();
                    Log.d("Servidor", "Cliente conectado: " + cliente.getInetAddress());
                    runOnUiThread(() -> Toast.makeText(this, "Cliente conectado!", Toast.LENGTH_SHORT).show());
                    new GerenciadorDeClientes(cliente, MainActivity.this);

                    synchronized (GerenciadorDeClientes.lock) {
                        System.out.println("Clientes conectados: " + GerenciadorDeClientes.clientesConectados);
                        if (GerenciadorDeClientes.clientesConectados == 2) {
                            System.out.println("Ambos os clientes estão conectados no servidor.");
                            Log.d("Servidor", "Ambos os clientes estão conectados");
                            runOnUiThread(() -> {
                                Toast.makeText(this, "Ambos os clientes conectados!", Toast.LENGTH_SHORT).show();
                                passarTela();
                            });
                            break;
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(this, "Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                try {
                    if (servidor != null)
                        servidor.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();

    }

    public void passarTela() {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
        finish();
    }
}