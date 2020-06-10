package com.example.chat_bot.service;

import com.example.chat_bot.modelo.Mensagem;
import com.example.chat_bot.modelo.RespostaServidor;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ChatService {
    @POST("message/text/send")
    Call<RespostaServidor> enviar(@Body Mensagem mensagem);


}




