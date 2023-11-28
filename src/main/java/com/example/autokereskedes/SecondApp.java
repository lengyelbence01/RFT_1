package com.example.autokereskedes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SecondApp extends Application {
    public void start(Stage stage) throws Exception{
        FXMLLoader fxmlLoader=new FXMLLoader(SecondApp.class.getResource("autohozzaadas-view.fxml"));
        Scene scene=new Scene(fxmlLoader.load());
        stage.setTitle("Új autó hozzáadása az autókereskedéshez");
        stage.setScene(scene);
        stage.setWidth(730);
        stage.setHeight(400);
        stage.showAndWait();

        setValues(fxmlLoader.getController());
    }
    public void setValues(SecondAppController secondAppController){
        labelMarka=secondAppController.labelMarka.getText();
        labelModell=secondAppController.labelModell.getText();
        labelEvjarat=secondAppController.labelEvjarat.getText();
        labelAr=secondAppController.labelAr.getText();
        labelKepLink=secondAppController.labelKepLink.getText();
    }
    public String labelMarka;
    public String labelModell;
    public String labelEvjarat;
    public String labelAr;
    public String labelKepLink;
}
