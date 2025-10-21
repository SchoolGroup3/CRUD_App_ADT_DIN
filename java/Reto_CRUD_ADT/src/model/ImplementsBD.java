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
    final String SQLMODIFYPROFILE = "UPDATE PROFILE SET EMAIL = ?, USER_NAME = ?, PSWD = ?, TELEPHONE = ?, NAME_ = ?<, SURNAME = ? WHERE PROFILE_CODE = ?";
    final String SQLMODIFYUSER = "UPDATE USER SET GENDER = ?, CARD_NO = ? WHERE PROFILE_CODE = ?";
    final String SQLMODIFY = "UPDATE ADMIN SET CURRENT_ACCOUNT = ? WHERE PROFILE_CODE = ?";

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
        Profile foundProfile = null; // Inicializamos como null
        this.openConnection(); // Abrimos la conexión a la base de datos

        try {
            // Preparamos la consulta SQL
            stmt = con.prepareStatement(SQLLOGING);
            stmt.setString(1, profile.getEmail()); // Establecemos el nombre de usuario
            stmt.setString(2, profile.getPssw()); // Establecemos la contraseña
            ResultSet resultado = stmt.executeQuery(); // Ejecutamos la consulta

            // Si hay un resultado, el usuario existe
            if (resultado.next()) {

                if (resultado instanceof Admin) { 
                   int profile_code = resultado.getInt("PROFILE_CODE");
                   String email = resultado.getString("EMAIL");
                   String username = resultado.getString("USER_NAME");
                   String password = resultado.getString("PSWD");
                   int telephone = resultado.getInt("TELEPHONE");
                   String name = resultado.getString("NAME_");
                   String surname = resultado.getString("SURNAME");
                   String current_account = resultado.getString("CURRENT_ACCOUNT");
                   foundProfile = new Admin(profile_code, email, username, password, telephone, name, surname, current_account);
                    
                }else if (resultado instanceof User){
                    int profile_code = resultado.getInt("PROFILE_CODE");
                   String email = resultado.getString("EMAIL");
                   String username = resultado.getString("USER_NAME");
                   String password = resultado.getString("PSWD");
                   int telephone = resultado.getInt("TELEPHONE");
                   String name = resultado.getString("NAME_");
                   String surname = resultado.getString("SURNAME");
                   String gender = resultado.getString("GENDER");
                   String card_no= resultado.getString("CARD_NO");
                   foundProfile = new User(profile_code,email, username, password, telephone, name,surname, gender, card_no);
                }
                

            }

            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al verificar credenciales: " + e.getMessage());
        }
        return foundProfile;

    }

    @Override
    public boolean modifyProfile(Profile profile) {
        boolean valid = false;
        this.openConnection();
        try {
            stmt = con.prepareStatement(SQLMODIFYPROFILE);
            stmt.setString(1, profile.getEmail());
            stmt.setString(2, profile.getUser_name());
            stmt.setString(2, profile.getPssw());
            stmt.setInt(2, profile.getTelephone());
            stmt.setString(2, profile.getName());
            stmt.setString(2, profile.getSurname());

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
    public boolean modifyUser(User user) {
        boolean valid = false;
        this.openConnection();
        try {
            stmt = con.prepareStatement(SQLMODIFYUSER);
            stmt.setString(1, user.getGender());
            stmt.setString(2, user.getCard_no());
            stmt.setInt(3, user.getProfile_code());
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
    public boolean modifyAdmin(Admin admin) {
        boolean valid = false;
        this.openConnection();
        try {
            stmt = con.prepareStatement(SQLMODIFYUSER);
            stmt.setString(1, admin.getCurrent_account());
            stmt.setInt(3, admin.getProfile_code());
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
