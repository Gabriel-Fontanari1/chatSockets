package com.example.chatsockets;

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

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Instancia o servidor em uma nova thread
        new Thread(() -> {
            ServerSocket servidor = null;
            try {
                runOnUiThread(() -> Toast.makeText(this, "Startando o servidor", Toast.LENGTH_SHORT).show());
                servidor = new ServerSocket(12345);
                runOnUiThread(() -> Toast.makeText(this, "Servidor startado", Toast.LENGTH_SHORT).show());

                while (true) {
                    Socket cliente = servidor.accept();
                    new GerenciadorDeClientes(cliente);
                }

            } catch (IOException e) {
                try {
                    if (servidor != null)
                        servidor.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                runOnUiThread(() -> Toast.makeText(this, "Porta ocupada, ou o servidor foi fechado", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
}