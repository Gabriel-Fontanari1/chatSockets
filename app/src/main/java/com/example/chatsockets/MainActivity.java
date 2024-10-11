package com.example.chatsockets;

import android.content.Intent;
import android.os.Bundle;
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

        // Inicia o servidor em uma nova thread
        new Thread(() -> {
            ServerSocket servidor = null;
            try {
                runOnUiThread(() -> Toast.makeText(this, "Iniciando o servidor", Toast.LENGTH_SHORT).show());
                servidor = new ServerSocket(54321);
                runOnUiThread(() -> Toast.makeText(this, "Servidor iniciado", Toast.LENGTH_SHORT).show());
                passarTela();

                while (true) {
                    Socket cliente = servidor.accept();
                    new GerenciadorDeClientes(cliente, MainActivity.this);
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
    }
}