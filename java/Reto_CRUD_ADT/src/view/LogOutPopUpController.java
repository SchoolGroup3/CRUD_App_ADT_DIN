package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LogOutPopUpController implements Initializable {

    @FXML
    private Button comfirm;

    @FXML
    private Button cancel;
    
    private Stage parent;
    
    public void setParentStage(Stage parent) {
        this.parent = parent;
    }

    @FXML
    private void canceled(ActionEvent event) {
        Stage actualStage = (Stage) cancel.getScene().getWindow();
        actualStage.close();
    }

    @FXML
    private void confirmed(ActionEvent event) {
        Stage stage = new Stage();
        Parent root;
        try {
            parent.close();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginWindow.fxml"));
            root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
