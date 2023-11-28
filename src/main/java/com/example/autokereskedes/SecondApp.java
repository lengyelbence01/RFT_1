package com.example.autokereskedes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SecondApp extends Application {
    public Stage stage;
    public void start(Stage stage) throws Exception{
        this.stage=stage;
        FXMLLoader fxmlLoader=new FXMLLoader(SecondApp.class.getResource("autohozzaadas-view.fxml"));
        Scene scene=new Scene(fxmlLoader.load());
        SecondAppController secondAppController=fxmlLoader.getController();
        secondAppController.secondApp=this;
        stage.setTitle("Új autó hozzáadása az autókereskedéshez");
        stage.setScene(scene);
        stage.setWidth(730);
        stage.setHeight(400);
        stage.showAndWait();
    }
    public void Close() {
        stage.close();
    }
}
