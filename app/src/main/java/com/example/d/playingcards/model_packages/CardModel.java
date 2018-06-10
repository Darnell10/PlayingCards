package com.example.d.playingcards.model_packages;

public class CardModel {

    private String value;
    private String suit;
    private ImageModel imageModel;
    private String image;

    public CardModel(String value, String suit, ImageModel imageModel, String image) {
        this.value = value;
        this.suit = suit;
        this.imageModel = imageModel;
        this.image = image;
    }

    public String getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public ImageModel getImageModel() {
        return imageModel;
    }

    public String getImage() {
        return image;
    }
}


