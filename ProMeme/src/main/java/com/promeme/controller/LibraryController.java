package com.promeme.controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LibraryController {
    public void openImage() throws IOException {
        File file = new FileChooser().showOpenDialog(null);
        new ImageWindowController().showImage(new Image(new FileInputStream(file)));
    }
    public void saveImage(ImageView image) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("image (.png)", "*.png"),
            new FileChooser.ExtensionFilter("image (.jpg)", "*.jpg")
        );

        File file = fileChooser.showSaveDialog(null);

        Image saveImage = image.getImage();
        ImageIO.write(SwingFXUtils.fromFXImage(saveImage, null), "png", file);
    }
}
