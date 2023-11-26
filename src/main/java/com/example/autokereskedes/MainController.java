package com.example.autokereskedes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainController {
    public Pane pane1;
    public ImageView img1;
    public Button sell1;
    public TextField text1;
    public Button lerazas1;
    public ImageView img2;
    public Button sell2;
    public Button lerazas2;
    public ImageView imgShowedOnScreen;
    public AnchorPane anchorPane;
    public Label label1;
    public Label label2;
    public ImageView img3;
    public Button sell3;
    public Button lerazas3;
    public Label label3;


    //Main-nek való átadáshoz
    public void initalize() throws FileNotFoundException {
        img1.setOnMouseMoved(this::showImageOnScreen);
        img1.setOnMouseExited(this::disAppearImage);
        autoKezelo();
    }

    public void autoKezelo(){

        AutoAdatKezelo autoAdatKezelo = new AutoAdatKezelo();
        List<Auto> osszesAuto = autoAdatKezelo.osszesAuto;


        File file = new File(osszesAuto.get(0).kep_link);
        Image image = new Image(file.toURI().toString());
        img1.setImage(image);

        label1.setText(osszesAuto.get(0).marka + " " + osszesAuto.get(0).modell +
                " (" + osszesAuto.get(0).evjarat + ")\n" +
                osszesAuto.get(0).ar + " Ft");

        file = new File(osszesAuto.get(1).kep_link);
        image = new Image(file.toURI().toString());
        img2.setImage(image);

        label2.setText(osszesAuto.get(1).marka + " " + osszesAuto.get(1).modell +
                " (" + osszesAuto.get(1).evjarat + ")\n" +
                osszesAuto.get(1).ar + " Ft");

        file = new File(osszesAuto.get(2).kep_link);
        image = new Image(file.toURI().toString());
        img3.setImage(image);

        label3.setText(osszesAuto.get(2).marka + " " + osszesAuto.get(2).modell +
                " (" + osszesAuto.get(2).evjarat + ")\n" +
                osszesAuto.get(2).ar + " Ft");
    }


    //Mouse moved - a kurzor képre vitel esetén megjelenik a kép nyagyított változat, kép nézegetőhöz
    public void showImageOnScreen(MouseEvent mouseEvent){
        imgShowedOnScreen.setVisible(true);
        //imgShowedOnScreen.setImage(img1.getImage());
    }

    //Mouse clicked - kattintás esetén az eddig kinagyított kép eltűnik, illetve az értékét is visszaállítja null-ra
    public void disAppearImage(MouseEvent mouseEvent) {
        if(imgShowedOnScreen.isVisible()){
            imgShowedOnScreen.setVisible(false);
            imgShowedOnScreen.setImage(null);
        }
    }
}