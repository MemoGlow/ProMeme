package com.test.view;

import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SaveDialogView {
    public static void display() throws IOException {
        FXMLLoader fxml = new FXMLLoader(SaveDialogView.class.getResource("SaveDialog.fxml"));
        Stage stage = fxml.load();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
