package com.test.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class SaveDialogController {
    @FXML
    Stage stage;
    @FXML
    Button saveButton;
    @FXML
    Button browseButton;
    @FXML
    Label saveLocation;
    public void setOnSaveAction(){
        stage.close();
    }
    public void setOnBrowseAction(){
        File file = new DirectoryChooser().showDialog(stage);
        saveLocation.setText(file.getAbsolutePath());
    }
}
