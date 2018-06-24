package com.example.d.playingcards.presentation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.d.playingcards.R;
import com.example.d.playingcards.model_packages.CardModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CardsRvAdapter extends RecyclerView.Adapter<CardsRvAdapter.CardViewHolder> {


    List<CardModel> cardModelList;

    public CardsRvAdapter(List<CardModel> cardModelList) {
        this.cardModelList = cardModelList;
    }


    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_itemview,parent,false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        CardModel cardModel = cardModelList.get(position);
        holder.onBind(cardModel);

    }


    @Override
    public int getItemCount() {
        return cardModelList.size();
    }

    public void moreCards(List<CardModel> cardModels){

        //Log.d("size?", cardModels.size()+"");
        cardModelList.addAll(cardModels);
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        private ImageView cardImageView;

        public CardViewHolder(View itemView) {
            super(itemView);

            cardImageView = itemView.findViewById(R.id.cards);
        }

        public void onBind(CardModel cardModel) {
            Picasso.with(itemView.getContext())
                    .load(cardModel.getImage())
                    .into(cardImageView);
        }
    }


}

