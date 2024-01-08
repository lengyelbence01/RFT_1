package com.example.autokereskedes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainController {
    public Main application;
    public ImageView imgShowedOnScreen;
    public AnchorPane anchorPane;
    public Button addCartoShopBTN;

    public int yEltolas=60;
    public int yElozoElhelyez=0;
    public Label osszesar;
    AutoAdatKezelo autoAdatKezelo = new AutoAdatKezelo();
    List<Auto> osszesAuto = autoAdatKezelo.osszesAuto;

    //Main-nek való átadáshoz
    public void initalize(){
        autoKezelo();
        addCartoShopBTN.setOnAction(this::addNewCartoShop);
        sumAllCarPrices();
    }


    //Új Pane beszúrása az AnchorPanebe
    public void autoKezelo(){
        for(int i=0;i<osszesAuto.size();i++){
            Pane pane=createPane(i);
            anchorPane.getChildren().add(pane);
        }
    }

    //Új Pane létrehozása megjelenítéshez
    public Pane createPane(int index) {
        Pane pane = new Pane();
        pane.setPrefHeight(240);
        pane.setPrefWidth(200);

        if(index==0){
            pane.setLayoutY(60);
        }else if(index % 6 == 0) {
            yElozoElhelyez++;
        }

        pane.setLayoutY(yElozoElhelyez * 240 + yEltolas);

        if (index % 6 == 0) {
            pane.setLayoutX(60);
        } else {
            pane.setLayoutX(60 + (index % 6) * 220);
        }
        //ImageView létrehozása - image hozzáadás
        ImageView imageView = new ImageView();
        imageView.setId("img"+index);
        imageView.setFitHeight(133);
        imageView.setFitWidth(200);


        File file = new File(osszesAuto.get(index).kep_link);
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);


        imageView.setOnMouseMoved(this::showImageOnScreen);
        imageView.setOnMouseExited(this::disAppearImage);

        pane.getChildren().add(imageView);

        //Értékesítve gomb és id létrehozása
        Button sellButton = new Button("Értékesítve");
        sellButton.setLayoutX(113);
        sellButton.setLayoutY(201);
        // Egyedi fx:id beállítása
        sellButton.setId("sell" + index);
        sellButton.setOnAction(this::sellCar);
        pane.getChildren().add(sellButton);

        //Label létrehozása
        Label label = new Label();
        // Egyedi fx:id beállítása
        label.setId("label"+index);

        label.prefWidth(200);
        label.prefHeight(66);
        label.setLayoutX(-1);
        label.setLayoutY(134);
        // - Szöveg hozzáadása a labelhez
        label.setText(osszesAuto.get(index).marka + " " + osszesAuto.get(index).modell +
                " (" + osszesAuto.get(index).evjarat + ")\n" +
                osszesAuto.get(index).ar + " Ft");
        pane.getChildren().add(label);

        //Visszatérési érték
        return pane;
    }

    //autó eladásához gomb -értékesítés
    public void sellCar(ActionEvent event) {
        try {
            Button sellButton = (Button) event.getSource();
            String idPRE = sellButton.getId();
            int id = Integer.parseInt(idPRE.substring(4));

            autoAdatKezelo.removeAuto(id);
            application.Refresh();
        } catch (Exception e) {
            e.printStackTrace(); // Hiba kiírása a konzolra
        }
    }


    //Mouse moved - a kurzor képre vitel esetén megjelenik a kép nyagyított változat, kép nézegetőhöz
    public void showImageOnScreen(MouseEvent mouseEvent){
        ImageView sourceImageView = (ImageView) mouseEvent.getSource();
        imgShowedOnScreen.setImage(sourceImageView.getImage());
        imgShowedOnScreen.setVisible(true);
    }

    //Mouse clicked - kattintás esetén az eddig kinagyított kép eltűnik, illetve az értékét is visszaállítja null-ra
    public void disAppearImage(MouseEvent mouseEvent) {
        if(imgShowedOnScreen.isVisible()){
            imgShowedOnScreen.setVisible(false);
            imgShowedOnScreen.setImage(null);
        }
    }

    public void addNewCartoShop(ActionEvent event) {
        application.Hide();
        addCartoShopBTN.setDisable(true);
        try {
            SecondApp secondApp = new SecondApp();
            secondApp.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        application.Refresh();
        addCartoShopBTN.setDisable(false);
    }
    public Integer sum=0;
    public void sumAllCarPrices(){
        for (int i=0;i<osszesAuto.size();i++){
            sum+= osszesAuto.get(i).ar;
        }
        osszesar.setText("Összes autó értéke: "+sum.toString() + "Ft");
    }
}