package com.example.d.playingcards.model_packages;

import java.util.List;

public class ResponseModel {

    private  boolean success;
    private  boolean shuffled;
    private String deck_id;
    private int remaining;
    private List<CardModel> cards;

    public ResponseModel(boolean success, boolean shuffled, String deck_id, int remaining, List<CardModel> cards) {
        this.success = success;
        this.shuffled = shuffled;
        this.deck_id = deck_id;
        this.remaining = remaining;
        this.cards = cards;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isShuffled() {
        return shuffled;
    }

    public String getDeck_id() {
        return deck_id;
    }

    public int getRemaining() {
        return remaining;
    }

    public List<CardModel> getcards() {
        return cards;
    }
}
