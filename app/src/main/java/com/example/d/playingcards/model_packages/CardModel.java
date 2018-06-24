package com.example.d.playingcards.model_packages;

public class CardModel {

    private String value;
    private String suit;
    private String image;
    private String code;

    public CardModel(String value, String suit, String image, String code) {
        this.value = value;
        this.suit = suit;
        this.image = image;
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public String getImage() {
        return image;
    }

    public String getCode() {
        return code;
    }
}


