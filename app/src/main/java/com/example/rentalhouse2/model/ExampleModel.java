package com.example.rentalhouse2.model;

public class ExampleModel {

    private String image, text,text1;

    public ExampleModel(String image, String text, String text1) {
        this.image = image;
        this.text = text;
        this.text1 = text1;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }
}
