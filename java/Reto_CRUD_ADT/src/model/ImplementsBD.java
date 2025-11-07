package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author USUARIO
 */
public class ImplementsBD implements UserDAO {

    // El pool
    private BasicDataSource dataSource;

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
        this.dataSource = DatabaseConnection.getDataSource();
    }

    @Override
    public Profile checkUser(String username, String passwd) {
        Profile foundProfile = null; // Inicializamos como null
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = dataSource.getConnection();
            stmt = con.prepareStatement(SQLLOGINGUSER);
            stmt.setString(1, username);
            stmt.setString(2, passwd);
            ResultSet result = stmt.executeQuery();
            if (!result.next()) {
                result.close();
                stmt.close();
                
                stmt = con.prepareStatement(SQLLOGINADMIN);
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
                
                System.out.println(foundProfile);
                return foundProfile;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar credenciales: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing statement: " + e.getMessage());
            }
            try {
                if (con != null) {
                    con.close(); // To always return de connection to the pool
                }
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }

        //foundProfile.toString(); //debug
        return foundProfile;
    }

    @Override
    public boolean modifyUser(User user) {
        new Thread(()-> {
            
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = dataSource.getConnection();
            stmt = con.prepareStatement(SQLMODIFYUSER);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getUser_name());
            stmt.setInt(3, user.getTelephone());
            stmt.setString(4, user.getName());
            stmt.setString(5, user.getSurname());
            stmt.setString(6, user.getGender());
            stmt.setString(7, user.getCard_no());
            stmt.setInt(8, user.getProfile_code());

            stmt.executeUpdate();
                

        } catch (SQLException e) {
            System.out.println("An error occurred.");
            if(Thread.interrupted()){
                System.out.println("muchas conexiones");
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing statement: " + e.getMessage());
            }
            try {
                if (con != null) {
                    con.close(); // To always return de connection to the pool
                }
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
        }).start();
        return true;
    }


    @Override
    public boolean modifyAdmin(Admin user) {
        new Thread(()-> {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = dataSource.getConnection();
            stmt = con.prepareStatement(SQLMODIFYADMIN);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getUser_name());
            stmt.setInt(3, user.getTelephone());
            stmt.setString(4, user.getName());
            stmt.setString(5, user.getSurname());
            stmt.setString(6, user.getCurrent_account());           
            stmt.setInt(7, user.getProfile_code());
            
            stmt.executeUpdate();

            
        } catch (SQLException e) {
            System.out.println("An error occurred." + e);
            if(Thread.interrupted()){
                System.out.println("muchas conexiones");
            }
        }finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing statement: " + e.getMessage());
            }
            try {
                if (con != null) {
                    con.close(); // To always return de connection to the pool
                }
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
         }).start();
        return true;
    }

    @Override
    public boolean modifyPassword(Profile user, String newPassword) {
        new Thread(()-> {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = dataSource.getConnection();
            stmt = con.prepareStatement(SQLMODIFYPASSWD);
            stmt.setString(1, newPassword);
            stmt.setInt(2, user.getProfile_code());
            
            stmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println("An error occurred.");
            if(Thread.interrupted()){
                System.out.println("muchas conexiones");
            }
        }finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing statement: " + e.getMessage());
            }
            try {
                if (con != null) {
                    con.close(); // To always return de connection to the pool
                }
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
        }).start();
        return true;
    }

    @Override
    public boolean deleteUser(User user) {
        new Thread(()-> {
        
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = dataSource.getConnection();
            stmt = con.prepareStatement(SQLDELETEUSER);
            stmt.setInt(1, user.getProfile_code());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("An error occurred.");
            if(Thread.interrupted()){
                System.out.println("muchas conexiones");
            }
        }finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing statement: " + e.getMessage());
            }
            try {
                if (con != null) {
                    con.close(); // To always return de connection to the pool
                }
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
        }).start();
        return true;
    }

    @Override
    public Profile insertUser(String username, String password) {
        User foundProfile = null;
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = dataSource.getConnection();
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
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing statement: " + e.getMessage());
            }
            try {
                if (con != null) {
                    con.close(); // To always return de connection to the pool
                }
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
        return foundProfile;
    }


    @Override
    public HashMap<Integer, User> getAllUsers() {
        User user = null;
        ResultSet rs = null;
        HashMap<Integer, User> users = new HashMap<>();

        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = dataSource.getConnection();
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
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing ResultSet: " + e.getMessage());
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing Statement: " + e.getMessage());
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing Connection: " + e.getMessage());
            }
        }

        return users;
    }

   /* @Override
    public boolean modifyAdmin(Admin admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

}
