package com.promeme.ulti;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PreviewPane {
    public void showPane(AnchorPane pane){
        Stage stage = new Stage();
        stage.setScene(new Scene(pane));
        stage.show();
    }
}
