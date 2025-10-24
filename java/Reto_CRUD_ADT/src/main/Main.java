package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.dbcp2.BasicDataSource;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //Estaba puesto para login window y no funcionaba, igual prueba las cosas antes de pushearlas
        //Esto ha pasado en varios proyecto ya, PRUEBA ANTES DE PUSHEAR
        Parent root = FXMLLoader.load(getClass().getResource("/view/HomeAdminWindow.fxml"));

        Scene scene = new Scene(root);
        
        //BasicDataSource ds = new BasicDataSource();

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
