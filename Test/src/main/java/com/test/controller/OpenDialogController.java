package com.test.controller;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class OpenDialogController {
    public File file;
    @FXML
    public static Stage openStage;
    public static File showFileChooseDialog(){
        File file = new FileChooser().showOpenDialog(openStage);
        return file;
    }
}
