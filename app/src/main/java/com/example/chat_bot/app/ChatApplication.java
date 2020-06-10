package com.example.chat_bot.app;

import android.app.Application;

import com.example.chat_bot.service.ChatService;
import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatApplication extends Application {

    public ChatService getChatService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dialogflow-teste-aula.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChatService chatService = retrofit.create(ChatService.class);

        return chatService;
    }
}
