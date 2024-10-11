package com.example.chatsockets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_SENT = 1;
    private static final int VIEW_TYPE_RECEIVED = 2;

    private List<ChatMessage> chatMessages;
    private String usuarioAtual; // Usuário atual

    public RecyclerViewAdapter(List<ChatMessage> chatMessages, String usuarioAtual) {
        this.chatMessages = chatMessages;
        this.usuarioAtual = usuarioAtual;
    }

    // Metodo para verificar se a mensagem é enviada ou recebida.
    @Override
    public int getItemViewType(int position) {
        ChatMessage chatMessage = chatMessages.get(position);
        // Se o nome do usuário da mensagem é o mesmo do usuário atual
        // A mensagem é do tipo "enviada"
        if (chatMessage.getUsername().equals(usuarioAtual)) {
            return VIEW_TYPE_SENT;
        } else {
            return VIEW_TYPE_RECEIVED;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SENT) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_one_line_enviamsg, parent, false);
            return new SentMessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    public static class SentMessageViewHolder extends RecyclerView.ViewHolder {
        TextView messageText, userNameText;

        public SentMessageViewholder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById((R.id.textViewUsrMsg))
        }
    }
}