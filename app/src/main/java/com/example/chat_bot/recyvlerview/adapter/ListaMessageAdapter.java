package com.example.chat_bot.recyvlerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chat_bot.R;
import com.example.chat_bot.modelo.Mensagem;

import java.util.List;

public class ListaMessageAdapter extends RecyclerView.Adapter  {

    private final  int  VIEW_TYPE_MY_MESSAGE = 1;
    private  final int  VIEW_TYPE_BOT_MESSAGE = 2;
    private List<Mensagem> mensagems;
    private int idCor;
    private Context context;
    private Mensagem mensagem;
    private View LinhaMessage;


    public ListaMessageAdapter( List<Mensagem> mensagems,Context context, int idCor){
        this.context = context;
        this.mensagems = mensagems;
        this.idCor = idCor;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(idCor != VIEW_TYPE_MY_MESSAGE){
            LinhaMessage = LayoutInflater.from(context).inflate(R.layout.bot_message,parent, false);
            return new messageViewHolder_bot(LinhaMessage);

        }else{
            LinhaMessage = LayoutInflater.from(context).inflate(R.layout.my_mensagem,parent, false);
            return new messageViewHolder(LinhaMessage);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        mensagem = mensagems.get(position);
//        TextView texto = holder.itemView.findViewById(R.id.bot_message);
//        texto.setText(mensagem.getTexto());

            if(idCor != VIEW_TYPE_MY_MESSAGE){
               messageViewHolder_bot messageViewHolder_bot = (ListaMessageAdapter.messageViewHolder_bot) holder;
                messageViewHolder_bot.vincula(mensagem);
            }else{

                messageViewHolder messageViewHolder = (ListaMessageAdapter.messageViewHolder) holder;
                messageViewHolder.vincula(mensagem);
            }
    }

    @Override
    public int getItemCount() {
        return mensagems.size();
    }


    static class messageViewHolder_bot extends RecyclerView.ViewHolder{
        private  TextView texto;

        public messageViewHolder_bot(@NonNull View itemView) {
            super(itemView);
            texto = itemView.findViewById(R.id.bot_message);
        }

        public void vincula(Mensagem mensagem){

            texto.setText(mensagem.getTexto());
        }
    }



    class messageViewHolder extends RecyclerView.ViewHolder {
        private  TextView texto;

        public messageViewHolder(@NonNull View itemView ) {
            super(itemView);
                texto = itemView.findViewById(R.id.bot_message);

        }

        public void vincula(Mensagem mensagem){

            texto.setText(mensagem.getTexto());
        }

    }

}
