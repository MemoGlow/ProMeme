package com.promeme.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class EditableImage {
    private Image image;
    String description;
    ArrayList<EditableText> texts;
    Image toImage(){
        AnchorPane imageHolder = new AnchorPane();
        int idx = 0;
        for(EditableText text : texts){
            imageHolder.setTopAnchor(text, text.getLayoutY());
            imageHolder.setLeftAnchor(text, text.getLayoutX());
        }
        WritableImage writableImage = imageHolder.snapshot(null,null);
        return writableImage;
    }
}
