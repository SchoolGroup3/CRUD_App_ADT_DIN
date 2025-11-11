package Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import controller.Controller;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Admin;
import model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.testfx.api.FxAssert;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.WindowMatchers.isShowing;
import org.testfx.matcher.control.TableViewMatchers;
import view.AdminHomeWindowController;
import view.AdminModifyAdminController;
import view.AdminModifyUserController;
import view.DeleteAccountPopUpController;

/**
 *
 * @author 2dami
 */
public class AdminHomeWindowTest extends ApplicationTest {

    @Test
    public void testSetAdmin() {
        // Arrange (Preparar)
        AdminHomeWindowController controller = new AdminHomeWindowController();
        Admin admin = new Admin(1, "admin@test.com", "admin", "1234", 123456789, "admin", "admin", "E123456789");

        // Act (Actuar)
        controller.setAdmin(admin);

        // Assert (Comprobar)
        // Necesitas un método getAdmin() para verificar
        assertEquals(admin.getProfile_code(), 1);
        assertEquals(admin.getEmail(), "admin@test.com");
        assertEquals(admin.getUser_name(), "admin");
        assertEquals(admin.getPssw(), "1234");
        assertEquals(admin.getTelephone(), 123456789);
        assertEquals(admin.getName(), "admin");
        assertEquals(admin.getSurname(), "admin");
        assertEquals(admin.getCurrent_account(), "E123456789");

    }

  @Test
    public void testTableContent() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminHomeWindow.fxml"));
        Parent root = loader.load();
        AdminHomeWindowController controller = loader.getController();
        TableView adminTable = new TableView<>();

        
        ObservableList<User> usersData = controller.initialData();

        
        interact(() -> {
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("AdminHomeWindow");
            stage.show();
            FxAssert.verifyThat("adminTable", TableViewMatchers.containsRow("[carlosl, 3456-7890-1234-5678]"));
            stage.close();
        });
        

    }

    @Test
    public void buttonModifyUser() throws IOException {
        //button delete tested because you can click it
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminModifyUser.fxml"));
        Parent root = loader.load();
        AdminModifyUserController controller = loader.getController();

        interact(() -> {
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("AdminModifyUser");
            stage.show();
            verifyThat(window("AdminModifyUser"), isShowing());
            stage.close();
        });

    }
    
    @Test
    public void buttonDeleteUser() throws IOException {
        //button delete tested because you can click it
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DeleteAccountPopUp.fxml"));
        Parent root = loader.load();
        DeleteAccountPopUpController controller = loader.getController();

        interact(() -> {
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("AdminModifyUser");
            stage.show();
            verifyThat(window("AdminModifyUser"), isShowing());
            stage.close();
        });

    }

}
