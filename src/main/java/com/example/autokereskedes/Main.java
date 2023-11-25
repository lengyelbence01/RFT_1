package com.example.autokereskedes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("autokereskedes-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        MainController controller=fxmlLoader.getController();
        controller.initalize();
        //teljes képernyős window
        stage.setMaximized(true);
        stage.setTitle("Kókány autókereskedés bt.");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}