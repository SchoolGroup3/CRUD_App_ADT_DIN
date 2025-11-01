package view;

import controller.Controller;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Admin;
import model.User;

public class AdminModifyAdminController implements Initializable {

    @FXML
    private Label lblPasswordMessage;
    @FXML
    private Label lblSavedMessage;
    @FXML
    private TextField txtFieldName;
    @FXML
    private TextField txtFieldSurname;
    @FXML
    private TextField txtFieldEmail;
    @FXML
    private TextField txtFieldUsername;
    @FXML
    private TextField txtFieldPassword;
    @FXML
    private TextField txtFieldPhoneNumber;
    @FXML
    private Button btnChangePassword;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txtFieldAccountNumber;
    @FXML
    private TextField txtFieldProfileCode;
    @FXML
    private ImageView iconTrash;
    @FXML
    private ImageView iconHome;
    @FXML
    private Label lblHome;

    private Admin user;

    private Controller cont = new Controller();

    @FXML
    private void handleButtonActionSave(ActionEvent event) {
        String name;
        String surname;
        String email;
        String username;
        String password;
        int phoneNumber;
        String account_no;

        //Verifies if there are no fills empty and there are changes
        if (!isAnyFieldEmpty() && hasUserDataChanged()) {

            name = txtFieldName.getText();
            surname = txtFieldSurname.getText();
            email = txtFieldEmail.getText();
            username = txtFieldUsername.getText();
            phoneNumber = parseInt(txtFieldPhoneNumber.getText());
            account_no = txtFieldAccountNumber.getText();

            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setUser_name(username);
            user.setTelephone(phoneNumber);
            user.setCurrent_account(account_no);

            cont.modifyAdmin(user);
            lblSavedMessage.setText("Correctly modified");
            lblSavedMessage.setStyle("-fx-text-fill: green;");

        } else if (isAnyFieldEmpty()) {
            lblSavedMessage.setText("You have to complete all the fields");
        } else {
            lblSavedMessage.setText("No changes detected");
        }

    }

    @FXML
    private void handleButtonActionChangePassword(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ChangePasswdPopup.fxml"));
            Parent root = loader.load();

            //passing the user as a parameterS
            ChangePasswdPopupController controller = loader.getController();
            if (controller != null) {
                //controller.setUser(user);
            }

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Change Password Window");
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException("Error creating main window", e);
        }
    }

    public void setUser(Admin user) {
        this.user = user;
        if (txtFieldName != null) {
            loadData();
        }
    }

    public void openHomeWindow(MouseEvent event) {
        Stage stage = new Stage();
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminHomeWindow.fxml"));
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

    public void loadData() {
        //loads the texfields
        txtFieldName.setText(user.getName());
        txtFieldSurname.setText(user.getSurname());
        txtFieldEmail.setText(user.getEmail());
        txtFieldUsername.setText(user.getUser_name());
        txtFieldPhoneNumber.setText(String.valueOf(user.getTelephone()));
        txtFieldProfileCode.setText(String.valueOf(user.getProfile_code()));
        txtFieldAccountNumber.setText(user.getCurrent_account());
    }

    private boolean isAnyFieldEmpty() {
        return txtFieldName.getText().isEmpty()
                || txtFieldSurname.getText().isEmpty()
                || txtFieldEmail.getText().isEmpty()
                || txtFieldUsername.getText().isEmpty()
                || txtFieldPhoneNumber.getText().isEmpty()
                || txtFieldAccountNumber.getText().isEmpty();
    }

    private boolean hasUserDataChanged() {
        return !txtFieldName.getText().equals(user.getName())
                || !txtFieldSurname.getText().equals(user.getSurname())
                || !txtFieldEmail.getText().equals(user.getEmail())
                || !txtFieldUsername.getText().equals(user.getUser_name())
                || !txtFieldPhoneNumber.getText().equals(String.valueOf(user.getTelephone()))
                || !txtFieldAccountNumber.getText().equals(user.getCurrent_account());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iconTrash.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DeleteAccountPopUp.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException ex) {
                throw new RuntimeException("Error creating main window", ex);
            }
        });

        iconHome.setOnMouseClicked(event -> {
            openHomeWindow(event);
        });

        lblHome.setOnMouseClicked(event -> {
            openHomeWindow(event);
        });
    }

}
