package com.promeme.view;

import com.promeme.controller.EditableImageController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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

    public ArrayList<Text> getTexts() {
        return texts;
    }

    public void setTexts(ArrayList<Text> texts) {
        this.texts = texts;
    }
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @FXML
    ComboBox<String> colorMenu;

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

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    private EditableImageController editableImageController;
    @FXML
    AnchorPane imageHolder;
    @FXML
    ComboBox<String> fontFamilyMenu;

    @FXML
    public void setOnOpenButton() throws FileNotFoundException {

        FileChooser fileChooser = new FileChooser();
        image = new Image(new FileInputStream(fileChooser.showOpenDialog(null)));
        open(image);
    }
    public void open(Image image){
        while (imageHolder.getChildren().size() > 0) {
            setOnUndoButton(new ActionEvent());
        }
        this.image = image;
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(centerPane.getHeight());
        imageView.setFitWidth(centerPane.getWidth());

        scale = imageView.getFitWidth() / imageView.getImage().getWidth();
        scaleField.setText(String.valueOf(imageView.getFitWidth() * 100 / imageView.getImage().getWidth()));

        imageHolder.getChildren().add(imageView);
    }

    @FXML
    public void initialize() {
        assert imageHolder != null : "fx:id=\"imageHolder\" was not injected: check your FXML file 'editable-image-view.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'editable-image-view.fxml'.";

        // Khởi tạo combo box font family
        fontFamilyMenu.getItems().addAll(Font.getFamilies());
        fontFamilyMenu.setValue("Times New Roman");

        // khởi tạo combo box cho text fill color
        colorMenu.getItems().addAll(            "Red",
                "Green",
                "Blue",
                "Yellow",
                "Orange",
                "Purple",
                "Pink",
                "Cyan",
                "Black");

        // Khởi tạo controller cho editable image


        // Khởi tạo các thuộc tính ban đầu cho text
        colorMenu.setValue("Black");
        sizeField.setText("12.0");

        mode = 1;
        modeLabel.setText("Mode: View");
        // Khởi tạo các thuộc tính còn lại
        System.out.println("texts initalize");
        texts = new ArrayList<Text>();
        editableImageController = new EditableImageController(this);

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
            currentText = text;
            mode = 3;
            modeLabel.setText("Mode: Edit");
        }
    }

    public void updateText(Text text) {
        double size;
        try {
            size = Double.parseDouble(sizeField.getText());
        } catch (NumberFormatException ex) {
            size = 12;
        }
        text.setFont(new Font(fontFamilyMenu.getValue(), size));
        if(colorMenu.getValue() != null){
            switch (colorMenu.getValue()) {
                case "Red":
                    text.setFill(Color.RED);
                    break;
                case "Green":
                    text.setFill(Color.GREEN);
                    break;
                case "Blue":
                    text.setFill(Color.BLUE);
                    break;
                case "Yellow":
                    text.setFill(Color.YELLOW);
                    break;
                case "Orange":
                    text.setFill(Color.ORANGE);
                    break;
                case "Purple":
                    text.setFill(Color.PURPLE);
                    break;
                case "Pink":
                    text.setFill(Color.PINK);
                    break;
                case "Cyan":
                    text.setFill(Color.CYAN);
                    break;
                case "Black":
                    text.setFill(Color.BLACK);
                    break;
            }
        }
        System.out.println(fontFamilyMenu.getValue() + " " + size);

        text.setText(content.getText());

        sizeField.setText(String.valueOf(size));
    }

    @FXML
    void setOnUndoButton(ActionEvent event) {
        if (imageHolder.getChildren().size() > 0) {
            imageHolder.getChildren().remove(imageHolder.getChildren().size() - 1);
        }
    }

    @FXML
    void setOnExportButton(ActionEvent event) throws IOException {
        File file = new FileChooser().showSaveDialog(null);
        editableImageController.export(file);
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
        if(mode == 3){
            String sizeValue = null;
            if (event.getCode() == KeyCode.ENTER) {
                sizeValue = new String(sizeField.getText());
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
        }
    }
    @FXML
    void setOnDeleteButton(ActionEvent event) {
        if(currentText != null){
            texts.remove(currentText);
            imageHolder.getChildren().remove(currentText);
        }
    }
    @FXML
    void setOnFontChange(){
        if(mode == 3){
            System.out.println(1);
            updateText(currentText);
        }
    }
    @FXML
    void setOnColorChange(){
        if(mode == 3){
            updateText(currentText);
        }
    }
    @FXML
    void setOnIncreaseSizeButton(ActionEvent event) {
        double size = Double.parseDouble(sizeField.getText());
        size++;
        sizeField.setText(String.valueOf(size));
        if(mode == 3){
            updateText(currentText);
        }
    }

    @FXML
    void setOnDecreaseSizeButton(ActionEvent event) {
        double size = Double.parseDouble(sizeField.getText());
        size--;
        if(size <= 0){
            size = 12;
        }
        sizeField.setText(String.valueOf(size));
        if(mode == 3){
            updateText(currentText);
        }
    }
    @FXML
    public void setOnSaveButton() throws IOException {
        File file = new FileChooser().showSaveDialog(null);
        editableImageController.save(file);
    }
    void setMode(int mode){
        if(mode == 1){
            this.mode = mode;
            modeLabel.setText("Mode: Normal");
        }else if(mode == 2){
            this.mode = mode;
            modeLabel.setText("Mode: Select location");
        }else if(mode == 3){
            this.mode = mode;
            modeLabel.setText("Mode: Edit text");
        }
    }
}
