package com.promeme.controller;

import com.promeme.model.EditableImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class EditableImageController {

    private EditableImage editableImage;

    public EditableImage getEditableImage() {
        return editableImage;
    }
    public EditableImageController(){
        editableImage = new EditableImage();
    }

    public void setEditableImage(EditableImage editableImage) {
        this.editableImage = editableImage;
    }

    public EditableImageController(EditableImage editableImage){
        this.editableImage = editableImage;
    }
    public void setBaseImage(Image image){
        editableImage.setBaseImage(image);
    }
    public void addText(Text text, double scale){
        System.out.println(scale);
        Text outputText = new Text(text.getText());
        double size = text.getFont().getSize() / scale;
        outputText.setFont(new Font(text.getFont().getFamily(), size));
        outputText.setX(text.getX() /  scale);
        outputText.setY(text.getY() / scale);
        editableImage.getTexts().add(outputText);
    }
    public void export(File file, ArrayList<Text> texts, double scale) throws IOException {
        AnchorPane pane = new AnchorPane();
        pane.getChildren().add(new ImageView(editableImage.getBaseImage()));
        for(Text text : texts){
            addText(text, scale);
        }
        for(Text text : editableImage.getTexts()){
            pane.getChildren().add(text);
        }
        System.out.println(editableImage.getTexts().size());
        WritableImage exportImage = pane.snapshot(null, null);
        ImageIO.write(SwingFXUtils.fromFXImage(exportImage, null), "png", file);
    }
    public void open(File file) throws FileNotFoundException {
        editableImage.setBaseImage(new Image(new FileInputStream(file)));
    }

}
