package com.example.chatsockets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_ENVIADA = 1;
    private static final int VIEW_TYPE_RECEBIDA = 2;
    private List<ChatUsuario> listaChat;
    private String usuarioAtual;

    public RecyclerViewAdapter(List<ChatUsuario> listaChat, String usuarioAtual) {
        this.listaChat = listaChat;
        this.usuarioAtual = usuarioAtual;
    }

    @Override
    public int getItemViewType(int position) {
        ChatUsuario chatUsuario = listaChat.get(position);
        if (chatUsuario.getUsername().equals(usuarioAtual)) {
            return VIEW_TYPE_ENVIADA;
        } else {
            return VIEW_TYPE_RECEBIDA;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ENVIADA) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_one_line_enviamsg, parent, false);
            return new MensagemEnviadaViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_one_line_recebemsg, parent, false);
            return new MensagemRecebidaViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ChatUsuario chatUsuario = listaChat.get(position);

        if (holder.getItemViewType() == VIEW_TYPE_ENVIADA) {
            ((MensagemEnviadaViewHolder) holder).bind(chatUsuario);
        } else {
            ((MensagemRecebidaViewHolder) holder).bind(chatUsuario);
        }
    }

    @Override
    public int getItemCount() {
        return listaChat.size();
    }

    public static class MensagemEnviadaViewHolder extends RecyclerView.ViewHolder {
        TextView messageText, userNameText;

        public MensagemEnviadaViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.textViewUsrMsg);
            userNameText = itemView.findViewById(R.id.textViewUsrName);
        }

        void bind(ChatUsuario chatUsuario) {
            userNameText.setText("Você");
            messageText.setText(chatUsuario.getMensagem());
        }
    }

    public static class MensagemRecebidaViewHolder extends RecyclerView.ViewHolder {
        TextView messageText, userNameText;

        public MensagemRecebidaViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.textViewUsrMsg);
            userNameText = itemView.findViewById(R.id.textViewUsrName);
        }

        void bind(ChatUsuario chatUsuario) {
            userNameText.setText(chatUsuario.getUsername());
            messageText.setText(chatUsuario.getMensagem());
        }
    }
}