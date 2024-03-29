package com.promeme.model;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class EditableImage {
    private Image baseImage;
    private ArrayList<Text> texts;

    public EditableImage(){
        texts = new ArrayList<Text>();
    }
    public Image getBaseImage() {
        return baseImage;
    }

    public void setBaseImage(Image baseImage) {
        this.baseImage = baseImage;
    }

    public ArrayList<Text> getTexts() {
        return texts;
    }

    public void setTexts(ArrayList<Text> texts) {
        this.texts = texts;
    }
}
