package com.example.chat_bot.callback;

import android.widget.Toast;

import com.example.chat_bot.activity.MainActivity;
import com.example.chat_bot.modelo.Mensagem;
import com.example.chat_bot.modelo.RespostaServidor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnviarMensagemCallback implements Callback<RespostaServidor> {

    private  final int  VIEW_TYPE_OTHER_MESSAGE = 2;

    MainActivity activity;

    public EnviarMensagemCallback(MainActivity activity){
        this.activity = activity;
    }

    @Override
    public void onResponse(Call<RespostaServidor> call, Response<RespostaServidor> response) {

        if(response.isSuccessful()){

            Toast.makeText(activity.getApplicationContext(),"SUCESSO", Toast.LENGTH_SHORT).show();

            RespostaServidor respostaServidor = response.body();

            RespostaServidor id = respostaServidor;
            activity.colocaNaLista(new Mensagem(id.getTexto(),VIEW_TYPE_OTHER_MESSAGE));


        }  else {

            Toast.makeText(activity.getApplicationContext(),"Resposta ERRO", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onFailure(Call<RespostaServidor> call, Throwable t) {
        //        activity.ouvirMensagem();
    }



}