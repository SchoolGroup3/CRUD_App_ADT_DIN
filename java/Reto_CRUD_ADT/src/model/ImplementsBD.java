package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 *
 * @author USUARIO
 */
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
    final String SQLLOGINGUSER = "SELECT p.PROFILE_CODE, USER_NAME, PSWD, GENDER, CARD_NO FROM PROFILE_ p JOIN USER_ u ON p.PROFILE_CODE = u.PROFILE_CODE WHERE USER_NAME = ? AND PSWD = ?";
    final String SQLLOGINADMIN = "SELECT p.PROFILE_CODE, USER_NAME, PSWD,CURRENT_ACCOUNT FROM PROFILE_ p JOIN ADMIN_ u ON p.PROFILE_CODE = u.PROFILE_CODE WHERE USER_NAME = ? AND PSWD = ?";
    final String SQLMODIFYUSER = "UPDATE USER_ U JOIN PROFILE_ P ON U.PROFILE_CODE = P.PROFILE_CODE SET P.EMAIL = ?, P.USER_NAME = ?, P.TELEPHONE = ?, P.NAME_ = ?, P.SURNAME = ?, U.GENDER = ?, U.CARD_NO = ? WHERE P.PROFILE_CODE = ?";
    final String SQLMODIFYADMIN = "UPDATE ADMIN_ A JOIN PROFILE_ P ON A.PROFILE_CODE = P.PROFILE_CODE SET P.EMAIL = ?, P.USER_NAME = ?, P.TELEPHONE = ?, P.NAME_ = ?, P.SURNAME = ?, A.CURRENT_ACCOUNT = ? WHERE P.PROFILE_CODE = ?";
    final String SQLMODIFYPASSWD = "UPDATE PROFILE_ SET PSWD = ? WHERE PROFILE_CODE  = ?";
    final String SQLDELETEUSER = "DELETE U, P FROM USER_ U JOIN PROFILE_ P ON P.PROFILE_CODE = U.PROFILE_CODE WHERE U.PROFILE_CODE = ?";
    final String SQLGETUSERS = "SELECT * FROM PROFILE_ AS P, USER_ AS U WHERE P.PROFILE_CODE = U.PROFILE_CODE;";
    final String SQLMODIFYPROFILE = "UPDATE PROFILE SET EMAIL = ?, USER_NAME = ?, PSWD = ?, TELEPHONE = ?, NAME_ = ?<, SURNAME = ? WHERE PROFILE_CODE = ?";
    final String SQLMODIFY = "UPDATE ADMIN SET CURRENT_ACCOUNT = ? WHERE PROFILE_CODE = ?";
    final String SQLSIGNUP = "call RegistrarUsuario(?,?)";

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
    public Profile checkUser(String username, String passwd) {
        Profile foundProfile = null;
        this.openConnection();
        try {
            stmt = con.prepareStatement(SQLLOGINGUSER);
            stmt.setString(1, username);
            stmt.setString(2, passwd);
            ResultSet result = stmt.executeQuery();
            if (!result.next()) {
                stmt = con.prepareStatement(SQLLOGINGUSER);
                stmt.setString(1, username);
                stmt.setString(2, passwd);
                ResultSet result1 = stmt.executeQuery();
                if (result1.next()) {
                    int profile_code = result1.getInt("PROFILE_CODE");
                    String username1 = result1.getString("USER_NAME");
                    String password = result1.getString("PSWD");
                    foundProfile = new Admin(profile_code, null, username1, password, 000000000, null, null, null);
                    foundProfile.toString(); //debug
                    stmt.close();
                    con.close();
                    return foundProfile;
                } else {
                    stmt.close();
                    con.close();
                    return foundProfile;

                }
            } else {
                int profile_code = result.getInt("PROFILE_CODE");
                String username1 = result.getString("USER_NAME");
                String password = result.getString("PSWD");
                foundProfile = new User(profile_code, null, username1, password, 000000000, null, null, null, null);
                foundProfile.toString(); //debug
                stmt.close();
                con.close();
                return foundProfile;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar credenciales: " + e.getMessage());
        }

        foundProfile.toString(); //debug
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
            stmt.setString(6, user.getGender());
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
    public boolean modifyAdmin(Admin user) {
        boolean valid = false;
        this.openConnection();
        try {
            stmt = con.prepareStatement(SQLMODIFYADMIN);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getUser_name());
            stmt.setInt(3, user.getTelephone());
            stmt.setString(4, user.getName());
            stmt.setString(5, user.getSurname());
            stmt.setString(6, user.getCurrent_account());           
            stmt.setInt(7, user.getProfile_code());
            
            if (stmt.executeUpdate() > 0) {
                valid = true;
            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("An error occurred." + e);
        }
        return valid;
    }
    
    @Override
    public boolean modifyPassword(Profile user, String newPassword) {
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

    @Override
    public Profile insertUser(String username, String password) {
        User foundProfile = null;
        this.openConnection();

        try {
            stmt = con.prepareStatement(SQLSIGNUP);
            stmt.setString(1, username);
            stmt.setString(2, password);
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
            System.out.println("An error occurred: " + e);
        }

        return users;
    }

   /* @Override
    public boolean modifyAdmin(Admin admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

}
