package view;

import controller.Controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.User;

public class DeleteAccountPopUpController implements Initializable {

    private User user;

    @FXML
    private Button comfirm;

    @FXML
    private Button cancel;

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private void canceled(ActionEvent event) {
        Stage actualStage = (Stage) cancel.getScene().getWindow();
        actualStage.close();
    }

    @FXML
    private void confirmed(ActionEvent event) {
        Controller cont = new Controller();
        if (!cont.deleteUser(user)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("El usuario no se ha eliminado correctamente.");
            alert.showAndWait();
        } else {
            Stage stage = new Stage();
            Parent root;
            try {
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
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
