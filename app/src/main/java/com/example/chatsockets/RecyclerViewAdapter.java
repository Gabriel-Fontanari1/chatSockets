package com.example.chatsockets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //atributos
    private static final int VIEW_TYPE_ENVIADA = 1;
    private static final int VIEW_TYPE_RECEBIDA = 2;
    //lista das mensagens
    private List<ChatUsuario> listaChat;
    private String usuarioAtual;

    //construtor
    public RecyclerViewAdapter(List<ChatUsuario> listaChat, String usuarioAtual) {
        this.listaChat = listaChat;
        this.usuarioAtual = usuarioAtual;
    }

    // Metodo para verificar se a mensagem é enviada ou recebida.
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
        //puxar os layouts do escritor ou remetente
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
        //associa os dados da lista, ao item da recyclerview
        ChatUsuario chatUsuario = listaChat.get(position);

        if (holder.getItemViewType() == VIEW_TYPE_ENVIADA) {
            ((MensagemEnviadaViewHolder) holder).bind(chatUsuario);
        } else {
            ((MensagemRecebidaViewHolder) holder).bind(chatUsuario);
        }
    }

    @Override
    public int getItemCount() {
        //puxa o tamanho da lista pra recycle quantos itens prexisam ser exibidos
        return listaChat.size();
    }

    //segurar as viws que vão ser usadas na lista
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


    //exibir as mensagens recebidas
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