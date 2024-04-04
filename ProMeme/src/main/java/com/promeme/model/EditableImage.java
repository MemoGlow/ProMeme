package com.promeme.model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.ArrayList;

public class EditableImage implements Serializable {
    private String imagePath;
    private ArrayList<EditableText> texts;

    public EditableImage(){
        texts = new ArrayList<EditableText>();
    }
    public String getImagePath() throws FileNotFoundException {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public ArrayList<EditableText> getTexts() {
        return texts;
    }

    public void setTexts(ArrayList<EditableText> texts) {
        this.texts = texts;
    }
}
