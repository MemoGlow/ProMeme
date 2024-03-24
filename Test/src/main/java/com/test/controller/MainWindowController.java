package com.test.controller;

import com.test.view.OpenDialogView;
import com.test.view.SaveDialogView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class MainWindowController {
    Image image;
    @FXML
    Label centerImage;
    @FXML
    Stage mainStage;
    public void setOnChoosingOpen() throws IOException {
        ImageView image = new ImageView(new Image(new FileInputStream(OpenDialogView.disPlay())));
        image.setPreserveRatio(true);
        image.setFitHeight(200);
        centerImage.setGraphic(image);
    }
    public void setOnChoosingSave() throws IOException {
        SaveDialogView.display();
    }
}
