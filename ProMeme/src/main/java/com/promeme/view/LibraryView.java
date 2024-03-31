package com.promeme.view;

import com.promeme.controller.LibraryController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LibraryView {
    LibraryController libraryController;
    @FXML
    private GridPane gridPane;

    @FXML
    void initialize() throws IOException {
        assert gridPane != null : "fx:id=\"gridPane\" was not injected: check your FXML file 'library-view.fxml'.";

        libraryController = new LibraryController(this);
        libraryController.getData();

        int col = 0;
        int row = 1;

        gridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
        gridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
        gridPane.setMaxWidth(gridPane.getPrefWidth());

        gridPane.setMinHeight(Region.USE_COMPUTED_SIZE);
        gridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
        gridPane.setMaxHeight(gridPane.getPrefHeight());

        for(File file : libraryController.getLibrary().getImages()){
            System.out.println(file);
            FXMLLoader itemLoader = new FXMLLoader(ItemView.class.getResource("item.fxml"));
            VBox item = itemLoader.load();
            ItemView itemView = itemLoader.getController();
            itemView.setData(new Image(new FileInputStream(file)));
            gridPane.add(item, col++, row);
            GridPane.setMargin(item, new Insets(10));
            if(col == 3){
                row++;
                col = 0;
            }
        }
    }
}
