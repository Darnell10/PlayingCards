package com.example.d.playingcards;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.d.playingcards.model_packages.CardModel;
import com.example.d.playingcards.model_packages.ResponseModel;
import com.example.d.playingcards.presentation.CardsRvAdapter;
import com.example.d.playingcards.service.CardServiceApi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.d.playingcards.service.CardServiceApi.BASE_URL;

public class MainActivity extends AppCompatActivity {

    private CardsRvAdapter cardsRvAdapter;
    private CardServiceApi cardServiceApi;
    Retrofit retrofit;
    int cardsLeft;
    String shuffleDeck;

    @BindView(R.id.remaining_cards_textview) TextView remainingcards;

    @BindView(R.id.draw_cards_number) EditText draw_cards;

    @BindView(R.id.shuffle_cards_button) Button shuffle;

    @BindView(R.id.draw_button) Button draw;

    @BindView(R.id.card_recycler_view) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setRecyclerView();

        setRetrofit();

        refreshInOnCreate();


            shuffle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardServiceApi = retrofit.create(CardServiceApi.class);
                    Call<ResponseModel> call = cardServiceApi.getResponseModel();
                    call.enqueue(new Callback<ResponseModel>() {
                        @Override
                        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                            shuffleDeck = response.body().getDeck_id();
                            cardsLeft = response.body().getRemaining();
                            remainingcards.setText("You have "  + cardsLeft + " Cards Left ");
                            Log.e("SHUFFLE", shuffleDeck + "Remaining");

                        }

                        @Override
                        public void onFailure(Call<ResponseModel> call, Throwable t) {

                            Log.e("Something went Wrong", t.getLocalizedMessage());
                        }
                    });
                }
            });



        draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cardsPicked = Integer.parseInt(draw_cards.getText().toString());
                Log.d("Cards Picked", cardsPicked + "");
                Log.d("chained method", "" + draw_cards.getText().toString());
                if (cardsPicked < 1) {
                    draw_cards.setError(getString(R.string.error_1));
                } else if (cardsPicked > cardsLeft) {
                    draw_cards.setError("You Chose too many cards, you  have " + cardsLeft + " In the Deck");
                } else {

                    hideKeyPad();

                    cardServiceApi = retrofit.create(CardServiceApi.class);
                    Call<ResponseModel> call = cardServiceApi.getNewCards(shuffleDeck, cardsPicked);
                    Log.d("Draw Shuffle Value", "" + shuffleDeck);
                    call.enqueue(new Callback<ResponseModel>() {
                        @Override
                        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                            List<CardModel> newCards = response.body().getcards();
                            cardsRvAdapter.moreCards(newCards);
                            cardsRvAdapter.notifyDataSetChanged();

                            Log.d("New Card", " We have New Cards");
                        }

                        @Override
                        public void onFailure(Call<ResponseModel> call, Throwable t) {
                            Log.e("NO Cards", " Somethings Wrong");
                            t.printStackTrace();
                        }
                    });

                }
            }
        });


    }

    public void setRecyclerView(){
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        cardsRvAdapter = new CardsRvAdapter(new ArrayList<CardModel>());
        recyclerView.setAdapter(cardsRvAdapter);
    }

    public void setRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void refreshInOnCreate() {
        cardServiceApi = retrofit.create(CardServiceApi.class);
        Call<ResponseModel> call = cardServiceApi.getResponseModel();
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                shuffleDeck = response.body().getDeck_id();
                cardsLeft = response.body().getRemaining();
                remainingcards.setText("You have " + " " + cardsLeft + " Cards Left ");
                Log.e("SHUFFLE", shuffleDeck + "Remaining");

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

                Log.e("OnCreate New Cards", t.getLocalizedMessage());
            }
        });
    }

    public void hideKeyPad() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }


}


