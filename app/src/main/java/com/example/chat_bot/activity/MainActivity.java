package com.example.chat_bot.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chat_bot.R;
import com.example.chat_bot.adapter.MensagemAdapter;
import com.example.chat_bot.callback.EnviarMensagemCallback;
import com.example.chat_bot.modelo.Mensagem;
import com.example.chat_bot.modelo.RespostaServidor;
import com.example.chat_bot.recyvlerview.adapter.ListaMessageAdapter;
import com.example.chat_bot.service.ChatService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final  int  VIEW_TYPE_MY_MESSAGE = 1;
    private  final int  VIEW_TYPE_OTHER_MESSAGE = 2;

    private Button button;
    private int idClient = 1;
    private EditText editText;

    private List<Mensagem> mensagens;
    private ChatService chatService;
    private RecyclerView listaMessage;
    private RecyclerView.LayoutManager layoutManager;
    private ListaMessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtém o objeto RecyclerView.
        listaMessage =  findViewById(R.id.lista_message_recyclerview);
        mensagens = new ArrayList<>();

        // Defina o gerenciador de layout do RecyclerView.
        layoutManager = new LinearLayoutManager(this);
        listaMessage.setLayoutManager(layoutManager);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dialogflow-server-helppro-pi2.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        chatService = retrofit.create(ChatService.class);


        button =  findViewById(R.id.btn_enviar);
        editText = findViewById(R.id.et_texto);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mensagem mensagem = new Mensagem( editText.getText().toString(),VIEW_TYPE_MY_MESSAGE);


                Call<RespostaServidor> call = chatService.enviar(mensagem);
                call.enqueue(new EnviarMensagemCallback(MainActivity.this));
                colocaNaLista(mensagem);
                editText.setText(null);



            }
        });


    }

    public void colocaNaLista(Mensagem mensagem){
        mensagens.add(mensagem);


//        MensagemAdapter adapter = new MensagemAdapter(idClient,mensagens,this);
        ListaMessageAdapter adapter = new ListaMessageAdapter(mensagens,this,mensagem.getVIEW_TYPE());
        listaMessage.setAdapter(adapter);
        listaMessage.scrollToPosition(adapter.getItemCount() -1 );

    }



}