package com.promeme.view;

import com.promeme.controller.LibraryController;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class LibraryView {
    Stage ownerStage;

    public Stage getOwnerStage() {
        return ownerStage;
    }

    public void setOwnerStage(Stage ownerStage) {
        this.ownerStage = ownerStage;
    }

    @FXML
    public void openImage() throws IOException {
        new LibraryController().openImage();
    }
}
