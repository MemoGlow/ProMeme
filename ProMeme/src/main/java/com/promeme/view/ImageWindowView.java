package com.promeme.view;

import com.promeme.controller.LibraryController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ImageWindowView {
    Stage ownerStage;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private ImageView centerImage;


    public void setOwnerStage(Stage ownerStage) {
        this.ownerStage = ownerStage;
    }

    double scale;
    @FXML
    TextField scaleField;

    @FXML
    private ScrollPane centerPane;

    @FXML
    private HBox imageHolder;


    public void setCenterImage(Image image) throws IOException {
        centerImage.setImage(image);
        centerImage.setPreserveRatio(true);
        centerPane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(oldValue.doubleValue() != 0){
                    centerImage.setFitHeight(centerImage.getFitHeight() * newValue.doubleValue() / oldValue.doubleValue());
                }
            }
        });
        centerPane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(oldValue.doubleValue() != 0){
                    centerImage.setFitWidth(centerImage.getFitWidth() * newValue.doubleValue() / oldValue.doubleValue());
                }
            }
        });
    }

    public void initialize() throws FileNotFoundException {
        imageHolder.setFillHeight(true);
    }

    @FXML
    void imageZoomIn(ActionEvent event) {
        scale = centerImage.getFitHeight() * 100 / centerImage.getImage().getHeight();
        scale += 3;
        centerImage.setFitWidth(centerImage.getImage().getWidth() * scale / 100);
        centerImage.setFitHeight(centerImage.getImage().getHeight() * scale / 100);
    }

    @FXML
    void imageZoomOut(ActionEvent event) {
        scale = centerImage.getFitHeight() * 100 / centerImage.getImage().getHeight();
        scale -= 3;
        if(scale < 0) scale += 3;
        centerImage.setFitWidth(centerImage.getImage().getWidth() * scale / 100);
        centerImage.setFitHeight(centerImage.getImage().getHeight() * scale / 100);
    }

    @FXML
    void setScale() {

    }

    @FXML
    void zoomToFit() {
        centerImage.setFitWidth(centerPane.getWidth());
        centerImage.setFitHeight(centerPane.getHeight());
    }
    @FXML
    void setOnSaveAsButton() throws IOException {
        new LibraryController().saveImage(centerImage);
    }
}
