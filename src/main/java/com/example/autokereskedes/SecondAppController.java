package com.example.autokereskedes;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class SecondAppController {
    public ImageView img;
    public Button submitBTN;
    public Label labelMarka;
    public Label labelAr;
    public Label labelModell;
    public Label labelEvjarat;
    public Label labelKepLink;
    public Button tallozas;

    public void initalzie(){
        tallozas.setOnAction(this::selectFileButtonOnAction);
    }

    public void addToAutoker(ActionEvent event) {

    }

    private void selectFileButtonOnAction(ActionEvent event) {
        FileChooser fc = new FileChooser();
        ArrayList<String> extensions = new ArrayList<>(Arrays.asList("*.jpeg", "*.jpg", "*.png", "*.bmp"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", extensions));
        File file = fc.showOpenDialog(null);
        if (file != null) {
            labelKepLink.setText(file.getAbsolutePath());
            try {
                Image image = new Image(new FileInputStream(file));
                img.setImage(image);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
