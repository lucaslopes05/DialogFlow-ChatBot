package com.example.chat_bot.modelo;

import com.google.gson.annotations.SerializedName;


public class RespostaServidor {

    @SerializedName("message")
    private String texto ;

    public String getTexto(){
        return texto;
    }


}
