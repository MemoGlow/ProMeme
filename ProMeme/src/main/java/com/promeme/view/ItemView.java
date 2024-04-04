package com.promeme.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ItemView {
    @FXML
    VBox imageHolder;

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    @FXML
    ImageView imageView;
    public void setData(Image image){
        imageView.setImage(image);
//        imageView.setPreserveRatio(true);
//        imageView.setFitWidth(imageHolder.getWidth());
//        imageView.setFitHeight(imageHolder.getHeight());
    }
    @FXML
    public void setOnClicked() throws IOException {
        FXMLLoader editableImageloader = new FXMLLoader(EditableImageView.class.getResource("editable-image-view.fxml"));

        AnchorPane pane = editableImageloader.load();

        EditableImageView editableImageView = editableImageloader.getController();


        Scene scene = new Scene(pane);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        editableImageView.loadImage(imageView.getImage());
    }
}
