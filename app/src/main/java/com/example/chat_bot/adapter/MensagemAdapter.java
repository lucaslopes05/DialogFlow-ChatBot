package com.example.chat_bot.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.chat_bot.R;
import com.example.chat_bot.modelo.Mensagem;

import java.util.List;
public class MensagemAdapter extends BaseAdapter {

    private List<Mensagem> mensagens;
    private Activity activity;
    private int idDoCliente;
    private View linha;
    private  TextView texto;

    public MensagemAdapter(int idDoCliente, List<Mensagem> mensagens, Activity activity) {
        this.mensagens = mensagens;
        this.activity = activity;
        this.idDoCliente = idDoCliente;
    }

    @Override
    public int getCount() {
        return mensagens.size();
    }

    @Override
    public Mensagem getItem(int i) {
        return mensagens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Mensagem mensagem = getItem(i);
         linha = activity.getLayoutInflater().inflate(R.layout.my_mensagem, viewGroup, false);
         texto = linha.findViewById(R.id.bot_message);

        if (idDoCliente != mensagem.getVIEW_TYPE()) {
             linha = activity.getLayoutInflater().inflate(R.layout.bot_message, viewGroup, false);
             texto = linha.findViewById(R.id.txtOtherMessage);
        }
        texto.setText(mensagem.getTexto());

        return linha;
    }
}