package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

/**
 * Main class that serves as the entry point for the JavaFX application.
 * Extends the JavaFX Application class to initialize and start the GUI application.
 */
public class Main extends Application {

    /**
     * The main entry point for the JavaFX application.
     * Loads the LoginWindow FXML file and sets up the primary stage.
     *
     * @param stage the primary stage for this application
     * @throws Exception if loading the FXML file fails
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginWindow.fxml"));
        Parent root = loader.load();

        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Main method that launches the JavaFX application.
     *
     * @param args command line arguments passed to the application
     */
    public static void main(String[] args) {
        launch(args);
    }

}