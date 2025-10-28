/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ImplementsBD implements UserDAO {

    // Atributos
    private Connection con; //PASAR POR PARAMENTRO LA CONEXION
    private PreparedStatement stmt;

    // Los siguientes atributos se utilizan para recoger los valores del fichero de
    // configuración
    private ResourceBundle configFile;
    private String driverBD;
    private String urlBD;
    private String userBD;
    private String passwordBD;

    // Querys
    final String SQLLOGING = "SELECT * FROM usuario WHERE NOMBRE_USUARIO = ? AND CONTRASEÑA = ?";
    final String SQLMODIFYUSER = "UPDATE USER_ U JOIN PROFILE_ P ON U.PROFILE_CODE = P.PROFILE_CODE SET P.EMAIL = ?, P.USER_NAME = ?, P.TELEPHONE = ?, P.NAME_ = ?, P.SURNAME = ?, U.GENDER = ?, U.CARD_NO = ? WHERE P.PROFILE_CODE = ?";
    final String SQLMODIFYPASSWD = "UPDATE PROFILE_ SET PSWD = ? WHERE PROFILE_CODE  = ?";
    final String SQLDELETEUSER = "DELETE U, P FROM USER_ U JOIN PROFILE_ P ON P.PROFILE_CODE = U.PROFILE_CODE WHERE U.PROFILE_CODE = ?";

    public ImplementsBD() {
        this.configFile = ResourceBundle.getBundle("configClase");
        this.driverBD = this.configFile.getString("Driver");
        this.urlBD = this.configFile.getString("Conn");
        this.userBD = this.configFile.getString("DBUser");
        this.passwordBD = this.configFile.getString("DBPass");
    }


    private void openConnection() {
        try {
            con = DriverManager.getConnection(urlBD, this.userBD, this.passwordBD);
        } catch (SQLException e) {
            System.out.println("Error al intentar abrir la BD");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Profile checkUser(Profile profile) {
        User foundUser = null; // Inicializamos como null
        this.openConnection(); // Abrimos la conexión a la base de datos

        try {
            // Preparamos la consulta SQL
            stmt = con.prepareStatement(SQLLOGING);
            stmt.setString(1, profile.getEmail()); // Establecemos el nombre de usuario
            stmt.setString(2, profile.getPssw()); // Establecemos la contraseña
            ResultSet resultado = stmt.executeQuery(); // Ejecutamos la consulta

            //HAY QUE COPIAR LOS DATOS DEPENDIENDO DE SI ES ADMIN O USER, PARA MIEKL 
            // Si hay un resultado, el usuario existe
            if (resultado.next()) {
                // Obtenemos los datos del usuario de la base de datos	
                String dni = resultado.getString("DNI"); 
                int edad = resultado.getInt("EDAD"); 
                String email = resultado.getString("EMAIL"); 

                //CREAR DEPENDIENDO DE SI ES ADMIN O USER, SE LO DEJO A MIKEL
                //foundUser = new User(profile.getUser_name(), dni, edad, email);
            }

            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al verificar credenciales: " + e.getMessage());
        }
        return foundUser;
        
        
    }

    @Override
    public boolean modifyUser(User user) {
        boolean valid = false;
        this.openConnection();
        try {
            stmt = con.prepareStatement(SQLMODIFYUSER);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getUser_name());
            stmt.setInt(3, user.getTelephone());
            stmt.setString(4, user.getName());
            stmt.setString(5, user.getSurname());
            stmt.setString(6,user.getGender());
            stmt.setString(7, user.getCard_no());           
            stmt.setInt(8, user.getProfile_code());   
            
            if (stmt.executeUpdate() > 0) {
                valid = true;
            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("An error occurred.");
        }
        return valid;
    }
    
    @Override
    public boolean modifyPassword(User user, String newPassword) {
        boolean valid = false;
        this.openConnection();
        try {
            stmt = con.prepareStatement(SQLMODIFYPASSWD);
            stmt.setString(1, newPassword);          
            stmt.setInt(2, user.getProfile_code());  
            if (stmt.executeUpdate() > 0) {
                valid = true;
            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("An error occurred.");
        }
        return valid;
    }

    @Override
    public boolean deleteUser(User user) {
        boolean valid = false;
        this.openConnection();
        try {
            stmt = con.prepareStatement(SQLDELETEUSER);      
            stmt.setInt(1, user.getProfile_code());  
            if (stmt.executeUpdate() > 0) {
                valid = true;
            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("An error occurred.");
        }
        return valid;
    }

}

