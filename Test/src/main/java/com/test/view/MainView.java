package com.test.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainView{
    public static void display() throws IOException {
        FXMLLoader fxml = new FXMLLoader(MainView.class.getResource("MainWindow.fxml"));
        Stage stage = fxml.load();
        stage.show();
    }
}
