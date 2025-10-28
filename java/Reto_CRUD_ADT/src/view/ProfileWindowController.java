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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.User;

public class ProfileWindowController implements Initializable {

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
    private TextField txtFieldCardNumber;
    @FXML
    private ImageView iconTrash;
    @FXML
    private ImageView iconHome;
    @FXML
    private ComboBox comboGender;

    private User user;

    private Controller cont = new Controller();

    @FXML
    private void handleButtonActionSave(ActionEvent event) {
        String name;
        String surname;
        String email;
        String username;
        String password;
        int phoneNumber;
        String cardNumber;
        String card_no;
        String gender;

        //Verifies if there are no fills empty and there are changes
        if (!isAnyFieldEmpty() && hasUserDataChanged()) {

            name = txtFieldName.getText();
            surname = txtFieldSurname.getText();
            email = txtFieldEmail.getText();
            username = txtFieldUsername.getText();
            phoneNumber = parseInt(txtFieldPhoneNumber.getText());
            card_no = txtFieldCardNumber.getText();
            gender = comboGender.getSelectionModel().getSelectedItem().toString();

            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setUser_name(username);
            user.setTelephone(phoneNumber);
            user.setCard_no(card_no);
            user.setGender(gender);

            cont.modifyUser(user);
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
                controller.setUser(user);
            }

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Change Password Window");
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException("Error creating main window", e);
        }
    }

    public void setUser(User user) {
        this.user = user;
        if (txtFieldName != null) {
            loadData();
        }
    }

    public void loadData() {

        //loads the texfields
        txtFieldName.setText(user.getName());
        txtFieldSurname.setText(user.getSurname());
        txtFieldEmail.setText(user.getEmail());
        txtFieldUsername.setText(user.getUser_name());
        txtFieldPhoneNumber.setText(String.valueOf(user.getTelephone()));

        txtFieldCardNumber.setText(user.getCard_no());
        comboGender.getSelectionModel().select(user.getGender());

    }

    private boolean isAnyFieldEmpty() {
        return txtFieldName.getText().isEmpty()
                || txtFieldSurname.getText().isEmpty()
                || txtFieldEmail.getText().isEmpty()
                || txtFieldUsername.getText().isEmpty()
                || txtFieldPhoneNumber.getText().isEmpty()
                || txtFieldCardNumber.getText().isEmpty()
                || comboGender.getSelectionModel().getSelectedItem() == null;
    }

    private boolean hasUserDataChanged() {
        return !txtFieldName.getText().equals(user.getName())
                || !txtFieldSurname.getText().equals(user.getSurname())
                || !txtFieldEmail.getText().equals(user.getEmail())
                || !txtFieldUsername.getText().equals(user.getUser_name())
                || !txtFieldPhoneNumber.getText().equals(String.valueOf(user.getTelephone()))
                || !txtFieldCardNumber.getText().equals(user.getCard_no())
                || !comboGender.getSelectionModel().getSelectedItem().equals(user.getGender());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //fill the combobox
        comboGender.getItems().addAll("Masculino", "Femenino", "Otro");
        comboGender.setEditable(false);

        iconTrash.setOnMouseClicked(event -> {
            //abrir pop up delete account

        });

        iconHome.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeWindow.fxml"));
                Parent root = loader.load();

                /*
            ChangePasswdPopupController controller = loader.getController();
            if (controller != null) {
                controller.setUser(user); 
            }*/
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Home Window");
                stage.show();

            } catch (IOException e) {
                throw new RuntimeException("Error creating main window", e);
            }
        });

    }

}
