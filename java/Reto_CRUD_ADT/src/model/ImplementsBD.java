package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
    final String SQLGETUSERS = "SELECT * FROM PROFILE_ AS P, USER_ AS U WHERE P.PROFILE_CODE = U.PROFILE_CODE;";
    final String SQLSIGNUP ="call RegistrarUsuario(?,?)";

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
            stmt.setString(1, profile.getSurname()); 
            stmt.setString(2, profile.getPssw());
            ResultSet resultado = stmt.executeQuery();

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
                   foundProfile = new Admin(email, username, password, telephone, name, surname, current_account);
                    
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
    
     //@Override
    public Profile insertUser(Profile profile) {
        User foundProfile = null;
        this.openConnection(); 
        
        try {
           stmt = con.prepareStatement(SQLSIGNUP);
            stmt.setString(1, profile.getUser_name());
            stmt.setString(2, profile.getPssw());
            ResultSet resultado = stmt.executeQuery(); 
             if (resultado.next()) {
            foundProfile = new User();
            foundProfile.setProfile_code(resultado.getInt("PROFILE_CODE"));
            foundProfile.setUser_name(resultado.getString("USER_NAME"));
            foundProfile.setPssw(resultado.getString("PSWD"));
        }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al verificar credenciales: " + e.getMessage());
        }
        return foundProfile;
    }
    
    @Override
    public HashMap<Integer, User> getAllUsers() {
        User user = null;
        ResultSet rs = null;
        HashMap<Integer, User> users = new HashMap<>();

        this.openConnection();

        try {
            stmt = con.prepareStatement(SQLGETUSERS);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                user = new User();
                user.setProfile_code(rs.getInt("PROFILE_CODE"));
                user.setEmail(rs.getString("EMAIL"));
                user.setUser_name(rs.getString("USER_NAME"));
                user.setPssw(rs.getString("PSWD"));
                user.setTelephone(rs.getInt("TELEPHONE"));
                user.setName(rs.getString("NAME_"));
                user.setSurname(rs.getString("SURNAME"));
                user.setGender(rs.getString("GENDER"));
                user.setCard_no(rs.getString("CARD_NO"));

                users.put(user.getProfile_code(), user);
            }
            
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("An error occurred: "+e);
        }
        
        return users;
    }

}
