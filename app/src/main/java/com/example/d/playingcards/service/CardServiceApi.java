package com.example.d.playingcards.service;

import com.example.d.playingcards.model_packages.ResponseModel;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CardServiceApi {


    //https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1
    String BASE_URL = "https://deckofcardsapi.com/api/deck/";


    @GET("{deck_id}/draw/?count=num_cards")
    Call<ResponseModel> getNewCards(@Path("deck_id") String newCards, @Query("count")int num_cards);

    @GET("new/shuffle/")
    Call<ResponseModel> getResponseModel();



}
