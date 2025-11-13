package view;

import controller.Controller;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.*;

/**
 * Controller for the admin home window that manages user administration operations.
 * Handles displaying users in a table, editing user accounts, and administrative functions.
 */
public class AdminHomeWindowController implements Initializable {

    @FXML
    private Label label;
    @FXML
    public TableView<User> adminTable;
    @FXML
    private TableColumn<User, String> user_name;
    @FXML
    private TableColumn<User, String> card_no;
    @FXML
    private TableColumn buttons;
    @FXML
    private ImageView logOut;
    
    private Controller cont = new Controller();
    private HashMap<Integer, User> users = cont.getAllUsers();
    private ObservableList<User> observableUsers;

    private Admin admin;

    /**
     * Sets the admin user for this controller.
     * 
     * @param admin the admin user to set
     */
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    
    /**
     * Refreshes the table data by reloading all users from the database.
     */
    public void refreshTableData() {
        this.users = cont.getAllUsers();
        adminTable.refresh();
    }
    
    /**
     * Applies the controller reference to the user modification controller.
     * 
     * @param controller the AdminModifyUserController to configure
     */
    private void applyControllerUserModify(AdminModifyUserController controller){
        controller.setController(this);
    }
    
    /**
     * Applies the controller reference to the delete account popup controller.
     * 
     * @param controller the DeleteAccountPopUpController to configure
     */
    private void applyControllerDeletePopup(DeleteAccountPopUpController controller){
        controller.setController(this);
    }

    /**
     * Opens the admin settings window for modifying admin account details.
     * 
     * @param event the mouse event that triggered this action
     */
    @FXML
    private void settingsWindow(MouseEvent event) {
        Stage stage = new Stage();
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminModifyAdmin.fxml"));
            root = loader.load();

            AdminModifyAdminController controller = loader.getController();
            controller.setAdmin((Admin) this.admin);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Opens the logout confirmation popup window.
     * 
     * @param event the mouse event that triggered this action
     */
    @FXML
    private void logOutPopup(MouseEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LogOutPopUp.fxml"));
            root = loader.load();
            LogOutPopUpController controller = loader.getController();
            controller.setParentStage((Stage) logOut.getScene().getWindow());
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Initializes the observable list with user data for the table view.
     * 
     * @return an ObservableList containing all users from the database
     */
    public ObservableList<User> initialData() {
        return FXCollections.observableArrayList(users.values());
    }

    /**
     * Initializes the controller class. Sets up table columns, cell factories,
     * and button actions for the user administration table.
     * 
     * @param url the location used to resolve relative paths for the root object, or null if unknown
     * @param rb the resources used to localize the root object, or null if not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user_name.setCellValueFactory(new PropertyValueFactory<User, String>("user_name"));
        card_no.setCellValueFactory(new PropertyValueFactory<User, String>("card_no"));
        buttons.setCellFactory(col -> new TableCell<User, Void>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");

            {   
                System.out.println(col);
                // Style buttons
                editButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
                deleteButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");

                //Button onClick methods
                editButton.setOnAction((new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            User user = getTableView().getItems().get(getIndex());

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminModifyUser.fxml"));
                            Parent root = loader.load();

                            AdminModifyUserController controller = loader.getController();
                            controller.setUser(user);
                            applyControllerUserModify(controller);

                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.show();
                        } catch (IOException ex) {
                            throw new RuntimeException("Error creating modify user window", ex);
                        }
                    }
                }));

                deleteButton.setOnAction((new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        User user = getTableView().getItems().get(getIndex());
                        System.out.println("Delete clicked for: " + user.getUser_name());

                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DeleteAccountPopUp.fxml"));
                            Parent root = loader.load();

                            DeleteAccountPopUpController controller = loader.getController();
                            controller.setUser(user);
                            controller.fromAdminWindow(true);
                            controller.setParentStage((Stage) deleteButton.getScene().getWindow());
                            applyControllerDeletePopup(controller);

                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.show();

                        } catch (IOException ex) {
                            throw new RuntimeException("Error creating delete popup", ex);
                        }
                    }
                }));
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    javafx.scene.layout.HBox buttonsBox = new javafx.scene.layout.HBox(10, editButton, deleteButton);
                    setGraphic(buttonsBox);
                }
            }
        });
        
        adminTable.setItems(initialData());
    }
}