package com.promeme.view;

import com.promeme.controller.EditableImageController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class EditableImageView {
    private Text currentText;
    private ArrayList<Text> texts;
    @FXML
    TextField sizeField;
    @FXML
    private Label modeLabel;
    int mode;
    @FXML
    TextArea content;
    @FXML
    private TextField scaleField;
    @FXML
    ScrollPane centerPane;
    @FXML
    BorderPane pane;
    private double scale;
    private EditableImageController editableImageController;
    @FXML
    AnchorPane imageHolder;

    @FXML
    public void setOnOpenButton() throws FileNotFoundException {
        while (imageHolder.getChildren().size() > 0) {
            setOnUndoButton(new ActionEvent());
        }
        FileChooser fileChooser = new FileChooser();
        editableImageController.setBaseImage(new Image(new FileInputStream(fileChooser.showOpenDialog(null))));
        ImageView image = new ImageView(editableImageController.getEditableImage().getBaseImage());
        imageHolder.getChildren().add(image);
        image.setPreserveRatio(true);
        scale = image.getFitWidth() / image.getImage().getWidth();
        scaleField.setText(String.valueOf(image.getFitWidth() * 100 / image.getImage().getWidth()));
    }

    @FXML
    void initialize() {
        assert imageHolder != null : "fx:id=\"imageHolder\" was not injected: check your FXML file 'editable-image-view.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'editable-image-view.fxml'.";
        editableImageController = new EditableImageController();
        texts = new ArrayList<Text>();
        mode = 1;
        modeLabel.setText("Mode: View");
        sizeField.setText("12.0");
    }

    @FXML
    void setOnChangeModeButtton(ActionEvent event) {
        if (mode == 1) {
            mode = 2;
            modeLabel.setText("Mode: Add");
        } else if (mode == 2) {
            mode = 3;
            modeLabel.setText("Mode: Edit");
        } else if (mode == 3) {
            mode = 1;
            modeLabel.setText("Mode: View");
            currentText = null;
        }
    }

    @FXML
    void setOnImageHolderClicked(MouseEvent event) {
        if (mode == 2) {
            Text text = new Text();
            text.setX(event.getX());
            text.setY(event.getY());
            updateText(text);
            imageHolder.getChildren().add(text);
            texts.add(text);
            text.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (mode == 3) {
                        currentText = text;
                        content.setText(text.getText());
                        sizeField.setText(String.valueOf(text.getFont().getSize()));
                    }
                }
            });
            text.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(mode == 3){
                        currentText = text;
                        text.setX(event.getX());
                        text.setY(event.getY());
                    }
                }
            });
        }
    }

    public void updateText(Text text) {
        double size;
        try {
            size = Integer.parseInt(sizeField.getText());
        } catch (NumberFormatException ex) {
            size = 12;
        }
        text.setFont(new Font(size));
        text.setText(content.getText());
    }

    @FXML
    void setOnUndoButton(ActionEvent event) {
        if (imageHolder.getChildren().size() > 0) {
            imageHolder.getChildren().remove(imageHolder.getChildren().size() - 1);
        }
    }

    @FXML
    void setOnSaveAsButton(ActionEvent event) throws IOException {
        File file = new FileChooser().showSaveDialog(null);
        editableImageController.export(file, texts, scale);
        System.out.println(imageHolder.getChildren().size());
    }

    @FXML
    void setOnFitButton(ActionEvent event) {

    }

    @FXML
    void setOnContentTyped(KeyEvent event) {
        if (mode == 3) {
            System.out.println("content typed");
            currentText.setText(content.getText());
        }
    }

    @FXML
    void setOnFontSizeTyped(KeyEvent event) {
        String sizeValue = null;
        if (event.getCode() == KeyCode.ENTER) {
            sizeValue = new String(sizeField.getText());
            System.out.println(1);
        } else {
            return;
        }
        double size;
        try {
            size = Double.parseDouble(sizeValue);
        } catch (Exception e) {
            size = 12;
        }
        updateText(currentText);
        sizeField.setText(String.valueOf(size));
    }
    @FXML
    void setOnDeleteButton(ActionEvent event) {
        if(currentText != null){
            texts.remove(currentText);
            imageHolder.getChildren().remove(currentText);
        }
    }
}
