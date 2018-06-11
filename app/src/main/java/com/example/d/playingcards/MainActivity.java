package com.example.d.playingcards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.d.playingcards.model_packages.CardModel;
import com.example.d.playingcards.model_packages.ResponseModel;
import com.example.d.playingcards.presentation.CardsRvAdapter;
import com.example.d.playingcards.service.CardServiceApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.d.playingcards.service.CardServiceApi.BASE_URL;

public class MainActivity extends AppCompatActivity {

    private TextView remainingcards;
    private EditText draw_cards;
    private Button shuffle;
    private Button draw;
    private RecyclerView recyclerView;
    private CardsRvAdapter cardsRvAdapter;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        remainingcards = findViewById(R.id.remaining_cards_textview);
        draw_cards = findViewById(R.id.draw_cards_number);
        shuffle = findViewById(R.id.shuffle_cards_button);
        draw = findViewById(R.id.draw_button);
        recyclerView = findViewById(R.id.card_recycler_view);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        cardsRvAdapter = new CardsRvAdapter(new ArrayList<CardModel>());
        recyclerView.setAdapter(cardsRvAdapter);


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }

    public void shufffleClick(){

        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardServiceApi cardServiceApi = retrofit.create(CardServiceApi.class);
                Call<ResponseModel> call = cardServiceApi.getResponseModel();
                call.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                       String  shuffleDeck = response.body().getDeck_id();
                       int cardsLeft = response.body().getRemaining();

                        Log.e("Shuffle", "Cards Shuffled");
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {

                    }
                });
            }
        });
    }

}
