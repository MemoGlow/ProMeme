package com.promeme.controller;

import com.promeme.view.ImageWindowView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ImageWindowController {
    public void showImage(Image image) throws IOException {
        Stage stage = new Stage();

        FXMLLoader fxml = new FXMLLoader(ImageWindowView.class.getResource("image-display.fxml"));
        Parent root = fxml.load();

        ImageWindowView imageWindowView = fxml.<ImageWindowView>getController();
        imageWindowView.setCenterImage(image);

        Scene scene = new Scene(root, 600, 400);

        stage.setScene(scene);
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.show();
    }
}
