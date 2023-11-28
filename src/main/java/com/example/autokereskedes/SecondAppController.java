package com.example.autokereskedes;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class SecondAppController extends MainController{
    public SecondApp secondApp;
    public ImageView img;
    public Button submitBTN;
    public Label labelKepLink;
    public Button tallozas;
    public TextField textfield1;
    public TextField textfield2;
    public TextField textfield3;
    public TextField textfield4;

    public void initalize(){
        tallozas.setOnAction(this::addImg);
        submitBTN.setOnAction(this::addToAutoker);
    }

    public void addToAutoker(ActionEvent event) {
        autoAdatKezelo.addAuto(textfield1.getText(),textfield2.getText(),
                textfield3.getText(),textfield4.getText(),labelKepLink.getText());
        secondApp.Close();
    }

    public void addImg(ActionEvent event) {
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
