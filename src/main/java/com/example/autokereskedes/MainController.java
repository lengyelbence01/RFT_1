package com.example.autokereskedes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MainController {
    public Pane pane1;
    public ImageView img1;
    public Button sell1;
    public TextField text1;
    public Button lerazas1;
    public ImageView img2;
    public Button sell2;
    public TextField text2;
    public Button lerazas2;
    public ImageView imgShowedOnScreen;


    //Main-nek való átadáshoz
    public void initalize(){
        img1.setOnMouseMoved(this::showImageOnScreen);
        pane1.setOnMouseClicked(this::disAppearImage);

    }

    public void TextBuilder(){

    }


    //Mouse moved - a kurzor képre vitel esetén megjelenik a kép nyagyított változat, kép nézegetőhöz
    public void showImageOnScreen(MouseEvent mouseEvent){
        imgShowedOnScreen.setVisible(true);
        imgShowedOnScreen.setImage(img1.getImage());

    }

    //Mouse clicked - kattintás esetén az eddig kinagyított kép eltűnik, illetve az értékét is visszaállítja null-ra
    public void disAppearImage(MouseEvent mouseEvent) {
        if(imgShowedOnScreen.isVisible()){
            imgShowedOnScreen.setVisible(false);
            imgShowedOnScreen.setImage(null);
        }
    }
}