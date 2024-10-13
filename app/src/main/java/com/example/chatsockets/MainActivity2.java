package com.example.chatsockets;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView recyclerViewMessages;
    EditText inputMensagem;
    Button btnEnviar;
    RecyclerViewAdapter adapter;
    List<ChatUsuario> listaChat = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        //findviews
        recyclerViewMessages = findViewById(R.id.recyclerViewMessages);
        inputMensagem = findViewById(R.id.inputMensagem);
        btnEnviar = findViewById(R.id.btnEnviar);

        //config do layoutmanager e do adapter
        recyclerViewMessages.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(listaChat, "SeuNomeDeUsuario");
        recyclerViewMessages.setAdapter(adapter);
        pressButton();
    }

    public void pressButton() {
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = inputMensagem.getText().toString().trim();
                if (!msg.isEmpty()) {
                    ChatUsuario novaMensagem = new ChatUsuario("SeuNomeDeUsuario", msg, "192.168.0.3");
                    listaChat.add(novaMensagem);
                    adapter.notifyItemInserted(listaChat.size() - 1);
                    inputMensagem.setText("");
                    recyclerViewMessages.scrollToPosition(listaChat.size() - 1);
                }
            }
        });
    }
}