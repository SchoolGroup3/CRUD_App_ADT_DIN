package Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.collections.ObservableList;
import model.Admin;
import model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import view.AdminHomeWindowController;
import view.AdminModifyAdminController;

/**
 *
 * @author 2dami
 */
public class AdminHomeWindowTest {

    @Test
    public void testSetAdmin() {
        // Arrange (Preparar)
        AdminHomeWindowController controller = new AdminHomeWindowController();
        Admin admin = new Admin(1, "admin@test.com", "admin", "1234", 123456789, "Admin", "User", "E123456789");

        // Act (Actuar)
        controller.setAdmin(admin);

        // Assert (Comprobar)
        // Necesitas un método getAdmin() para verificar
        assertEquals(admin.getProfile_code(), 1);
        assertEquals(admin.getEmail(), "admin@test.com");
        assertEquals(admin.getUser_name(), "admin");
        assertEquals(admin.getPssw(), "1234");
        assertEquals(admin.getTelephone(), 123456789);
        assertEquals(admin.getProfile_code(), "");

    }

    @Test
    public void testInitialData_UsuariosValidos() {

        AdminHomeWindowController controller = new AdminHomeWindowController();

        ObservableList<User> usuarios = controller.initialData();
        
        assertNotNull(usuarios);

        for (User user : usuarios) {

            assertNotNull(user.getUser_name());
            assertNotNull(user.getCard_no());
            assertNotNull(user.getEmail());

            assertFalse(user.getUser_name().isEmpty());
            assertFalse(user.getCard_no().isEmpty());
        }

        System.out.println("Todos los " + usuarios.size() + " usuarios tienen datos válidos");
    }
    
    @Test
    public void testTabla_MuestraTodoCorrectamente() {
        // Arrange
        AdminHomeWindowController controller = new AdminHomeWindowController();
        // Los datos YA están cargados automáticamente aquí:
        // private HashMap<Integer, User> users = cont.getAllUsers();

        // Act
        controller.initialize(null, null); // Esto pone los datos en la tabla

        // Assert
        ObservableList<User> datosEnTabla = controller.adminTable.getItems();

        assertNotNull("Table has data", datosEnTabla);
        assertFalse("Table is not empty", datosEnTabla.isEmpty());

        System.out.println("The table has " + datosEnTabla.size() + " users");

        // Mostrar qué hay en la tabla
        for (User user : datosEnTabla) {
            System.out.println(" - " + user.getUser_name() + " | " + user.getCard_no());
        }
    }
    
    
    

}
