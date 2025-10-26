package view;

import controller.Controller;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.User;


public class HomeAdminWindowController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TableView<User> adminTable;
    @FXML
    private TableColumn<User, String> user_name;
    @FXML
    private TableColumn<User, String> card_no;
    @FXML
    private TableColumn<User, String> email;
    
    private Controller cont = new Controller();
    private HashMap<Integer, User> users = cont.getAllUsers();

    @FXML
    private void handleButtonAction(ActionEvent event) {
        /*User newUser = new User();
        newUser.setUser_name("NewUser123");
        newUser.setCard_no("xxxx-xxxx-xxxx");
        newUser.setEmail("test@test.com");
        adminTable.getItems().add(newUser);*/
    }
    
    ObservableList<User> initialData(){        
        return FXCollections.observableArrayList(users.values());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user_name.setCellValueFactory(new PropertyValueFactory<User, String>("user_name"));
        card_no.setCellValueFactory(new PropertyValueFactory<User, String>("card_no"));
        email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        
        adminTable.setItems(initialData());
    }
}
