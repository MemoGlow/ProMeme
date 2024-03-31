package com.promeme;

import com.promeme.model.EditableImage;
import com.promeme.view.EditableImageView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.text.AttributeSet;
import java.awt.*;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxml = new FXMLLoader(EditableImageView.class.getResource("library-view.fxml"));
//        FXMLLoader fxml = new FXMLLoader(EditableImageView.class.getResource("editable-image-view.fxml"));
        Parent root = fxml.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();


//        AnchorPane layout = new AnchorPane();
//        Text text = new Text("Hello world");
//        text.setFont(new Font("Times New Roman", 150));
//        text.setLayoutX(50);
//        text.setLayoutY(300);
//        layout.getChildren().add(text);
//        Stage stage = new Stage();
//        stage.setScene(new Scene(layout));
//        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
