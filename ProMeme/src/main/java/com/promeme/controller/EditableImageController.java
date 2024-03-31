package com.promeme.controller;

import com.promeme.model.EditableImage;
import com.promeme.view.EditableImageView;
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
import java.io.*;
import java.util.ArrayList;

public class EditableImageController {

    private EditableImage editableImage;
    private EditableImageView editableImageView;
    public EditableImageController(EditableImageView editableImageView){
        this.editableImageView = editableImageView;
        editableImage = new EditableImage();
    }

    public EditableImageView getEditableImageView() {
        return editableImageView;
    }

    public void setEditableImageView(EditableImageView editableImageView) {
        this.editableImageView = editableImageView;
    }

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
    public void setEditableImage(){
        editableImage.setBaseImage(editableImageView.getImage());
        double scale = editableImageView.getScale();
        editableImage.setTexts(new ArrayList<Text>());
        for(Text text : editableImageView.getTexts()){
            System.out.println("editable image add");
            Text editableImageText = new Text();
            editableImageText.setFont(new Font(text.getFont().getFamily(), text.getFont().getSize() / scale));
            editableImageText.setFill(text.getFill());
            editableImageText.setX(text.getX() / scale);
            editableImageText.setY(text.getY() / scale);
            editableImage.getTexts().add(editableImageText);
            editableImageText.setText(text.getText());
            System.out.println(editableImageText);
        }
    }
    public void export(File file) throws IOException {
        setEditableImage();
        AnchorPane pane = new AnchorPane();
        pane.getChildren().add(new ImageView(editableImage.getBaseImage()));
        for(Text text : editableImage.getTexts()){
            pane.getChildren().add(text);
        }
        WritableImage exportImage = pane.snapshot(null, null);
        ImageIO.write(SwingFXUtils.fromFXImage(exportImage, null), "png", file);
    }
    public void open(File file) throws FileNotFoundException {
        editableImage.setBaseImage(new Image(new FileInputStream(file)));
    }
    public void save(File file) throws IOException {
        setEditableImage();
        ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(file));
        ous.writeObject(editableImage);
        ous.close();
    }

}
