package view;

import controller.Controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;

public class DeleteAccountPopUpController implements Initializable {

    private User user;
    private Controller cont = new Controller();

    @FXML
    private Button confirm;

    @FXML
    private Button cancel;

    private Stage parent;

    private boolean admin;

    public void fromAdminWindow(boolean admin) {
        this.admin = admin;
    }

    public void setParentStage(Stage parent) {
        this.parent = parent;
    }

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
        if (!cont.deleteUser(user)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("The user has not been deleted correctly.");
            alert.showAndWait();
        } else {
            try {
                // Cerrar el popup actual primero
                Stage currentPopupStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentPopupStage.close();
                
                if (!admin) {

                    if (parent != null) {
                        parent.close();
                    }

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginWindow.fxml"));
                    Parent root = loader.load();
                    Stage profileStage = new Stage();
                    profileStage.setScene(new Scene(root));
                    profileStage.show();
                }

                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}