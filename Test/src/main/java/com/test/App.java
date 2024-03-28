package com.test;

import com.test.view.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainView.display();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
