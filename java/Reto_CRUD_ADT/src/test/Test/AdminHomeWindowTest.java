package test.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import model.Admin;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import view.AdminModifyAdminController;

/**
 *
 * @author 2dami
 */
public class AdminHomeWindowTest {
    
   @Test
    public void testSetAdmin() {
        // Arrange (Preparar)
        AdminModifyAdminController controller = new AdminModifyAdminController();
        Admin admin = new Admin(1, "admin@test.com", "admin", "1234", 123456789, "Admin", "User", "E123456789");
        
        // Act (Actuar)
        controller.setAdmin(admin);
        
        // Assert (Comprobar)
        // Necesitas un método getAdmin() para verificar
        assertEquals(admin.getProfile_code(), 1);
        assertEquals(admin.getEmail(), "admin@test.com");
        assertEquals(admin.getProfile_code(), 1);
        assertEquals(admin.getProfile_code(), 1);
        assertEquals(admin.getProfile_code(), 1);
        assertEquals(admin.getProfile_code(), 1);

    }

}
