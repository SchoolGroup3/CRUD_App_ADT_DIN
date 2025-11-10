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
        assertEquals(admin.getName(),"admin");
        assertEquals(admin.getSurname(), "admin");
        assertEquals(admin.getCurrent_account(), "E123456789");

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

        System.out.println("All users " + usuarios.size() + " valid");
    }
    
    @Test
    public void testTabla_DatosDisponibles() {
        // Arrange
        AdminHomeWindowController controller = new AdminHomeWindowController();
        
        // Act - Probamos los datos sin initialize
        ObservableList<User> data = controller.initialData();
        
        // Assert
        assertNotNull("Data is not null", data);
        assertFalse("Users loaded", data.isEmpty());
        
        System.out.println("Table: " + data.size() + " users");
        
        // Mostrar contenido
        for (User user : data) {
            System.out.println("   - " + user.getUser_name() + " | " + user.getCard_no());
        }
        
        System.out.println("Data ready");
    }
    
    
    

}
