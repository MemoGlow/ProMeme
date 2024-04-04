package com.promeme.controller;

import com.promeme.model.EditableImage;
import com.promeme.model.EditableText;
import com.promeme.view.EditableImageView;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

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
        double scale = editableImageView.getScale();
        editableImage.setTexts(new ArrayList<EditableText>());
        for(EditableText text : editableImageView.getTexts()){
            EditableText editableImageText = new EditableText();
            editableImageText.setFont(new Font(text.getFont().getFamily(), text.getFont().getSize() / scale));
            editableImageText.setFill(text.getFill());
            editableImageText.setX(text.getX() / scale);
            editableImageText.setY(text.getY() / scale);
            editableImage.getTexts().add(editableImageText);
            editableImageText.setText(text.getText());
            System.out.println(editableImageText);
            System.out.println(editableImageText);
        }
    }
    public void export(File file) throws IOException {
        setEditableImage();
        AnchorPane pane = new AnchorPane();
        pane.getChildren().add(new ImageView(new Image(new FileInputStream(new File(editableImage.getImagePath())))));
        System.out.println(editableImage.getImagePath());
        for(EditableText text : editableImage.getTexts()){
            pane.getChildren().add(text);
        }
        WritableImage exportImage = pane.snapshot(null, null);
        ImageIO.write(SwingFXUtils.fromFXImage(exportImage, null), "png", file);
    }
    public void openFromImage(File file) throws FileNotFoundException {
        editableImage.setImagePath(file.getPath());
    }
    public void openFromProjectFile(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        System.out.println("Read object");
        editableImage = (EditableImage) ois.readObject();
        System.out.println(editableImage.getImagePath());
        System.out.println(editableImage.getTexts().get(0));
        ois.close();
    }
    public void saveToProjectFile(File file) throws IOException {
        setEditableImage();
        ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(file));
        ous.writeObject(editableImage);
        System.out.println("Write object");
        ous.close();
    }
}
