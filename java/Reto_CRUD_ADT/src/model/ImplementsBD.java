package model;

import exception.CustomException;
import java.sql.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Implementation of the UserDAO interface that provides database operations
 * for user management using connection pooling and prepared statements.
 * Handles both regular users and administrators with proper connection management.
 */
public class ImplementsBD implements UserDAO {

    // El pool
    private BasicDataSource dataSource;
    private ConnectionThread threadCon;

    // Queries
    final String SQLLOGINGUSER = "SELECT p.PROFILE_CODE, USER_NAME, PSWD,EMAIL, TELEPHONE, NAME_, SURNAME, GENDER, CARD_NO FROM PROFILE_ p JOIN USER_ u ON p.PROFILE_CODE = u.PROFILE_CODE WHERE USER_NAME = ? AND PSWD = ?";
    final String SQLLOGINADMIN = "SELECT p.PROFILE_CODE, USER_NAME, PSWD, EMAIL, TELEPHONE, NAME_, SURNAME, CURRENT_ACCOUNT FROM PROFILE_ p JOIN ADMIN_ u ON p.PROFILE_CODE = u.PROFILE_CODE WHERE USER_NAME = ? AND PSWD = ?";
    final String SQLMODIFYUSER = "UPDATE USER_ U JOIN PROFILE_ P ON U.PROFILE_CODE = P.PROFILE_CODE SET P.EMAIL = ?, P.USER_NAME = ?, P.TELEPHONE = ?, P.NAME_ = ?, P.SURNAME = ?, U.GENDER = ?, U.CARD_NO = ? WHERE P.PROFILE_CODE = ?";
    final String SQLMODIFYADMIN = "UPDATE ADMIN_ A JOIN PROFILE_ P ON A.PROFILE_CODE = P.PROFILE_CODE SET P.EMAIL = ?, P.USER_NAME = ?, P.TELEPHONE = ?, P.NAME_ = ?, P.SURNAME = ?, A.CURRENT_ACCOUNT = ? WHERE P.PROFILE_CODE = ?";
    final String SQLMODIFYPASSWD = "UPDATE PROFILE_ SET PSWD = ? WHERE PROFILE_CODE  = ?";
    final String SQLDELETEUSER = "DELETE U, P FROM USER_ U JOIN PROFILE_ P ON P.PROFILE_CODE = U.PROFILE_CODE WHERE U.PROFILE_CODE = ?";
    final String SQLGETUSERS = "SELECT * FROM PROFILE_ AS P, USER_ AS U WHERE P.PROFILE_CODE = U.PROFILE_CODE;";
    final String SQLMODIFYPROFILE = "UPDATE PROFILE SET EMAIL = ?, USER_NAME = ?, PSWD = ?, TELEPHONE = ?, NAME_ = ?<, SURNAME = ? WHERE PROFILE_CODE = ?";
    final String SQLMODIFY = "UPDATE ADMIN SET CURRENT_ACCOUNT = ? WHERE PROFILE_CODE = ?";
    final String SQLSIGNUP = "call RegistrarUsuario(?,?)";

    /**
     * Constructs a new ImplementsBD instance and initializes the data source
     * from the DatabaseConnection pool.
     */
    public ImplementsBD() {
        this.dataSource = DatabaseConnection.getDataSource();
    }

    /**
     * Verifies user credentials and returns the corresponding profile.
     * Checks both regular users and administrators in the database.
     *
     * @param username the username to verify
     * @param passwd the password to verify
     * @return the Profile object if credentials are valid, null otherwise
     */
    @Override
    public Profile checkUser(String username, String passwd) {
        Profile foundProfile = null; // Inicializamos como null
        Connection con = null;
        PreparedStatement stmt = null;
        PreparedStatement stm;

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
                    String email = result1.getString("EMAIL");
                    int telephone = result1.getInt("TELEPHONE");
                    String name = result1.getString("NAME_");
                    String surname = result1.getString("SURNAME");
                    String current_account = result1.getString("CURRENT_ACCOUNT");
                    foundProfile = new Admin(profile_code, email, username1, password, telephone, name, surname, current_account);
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
                String email1 = result.getString("EMAIL");
                int telephone1 = result.getInt("TELEPHONE");
                String name1 = result.getString("NAME_");
                String surname1 = result.getString("SURNAME");
                String gender1 = result.getString("GENDER");
                String card_no1 = result.getString("CARD_NO");
                foundProfile = new User(profile_code, email1, username1, password, telephone1, name1, surname1, gender1, card_no1);
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
        return foundProfile;
    }

    /**
     * Modifies an existing user's information in the database.
     *
     * @param user the User object containing updated information
     * @return true if the modification was successful, false otherwise
     * @throws CustomException if maximum connections are reached
     */
    @Override
    public boolean modifyUser(User user) {
        boolean valid = false;
        Connection con = null;
        PreparedStatement stmt = null;
        ConnectionThread threadCon;

        try {
            con = dataSource.getConnection();
            threadCon = new ConnectionThread(con);
            threadCon.start();
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
        } catch (SQLException e) {
            if (e.getMessage().toLowerCase().contains("timeout")) {              

                try {
                    throw new CustomException("Max connections reached! Wait a moment...", "SERVER BUSY");
                } catch (CustomException ex) {
                    Logger.getLogger(ImplementsBD.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } else {
                System.out.println("An error occurred.");
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing statement: " + e.getMessage());
            }

        }
        return valid;
    }

    /**
     * Modifies an existing administrator's information in the database.
     *
     * @param user the Admin object containing updated information
     * @return true if the modification was successful, false otherwise
     * @throws CustomException if maximum connections are reached
     */
    @Override
    public boolean modifyAdmin(Admin user) {
        Connection con = null;
        PreparedStatement stmt = null;
        boolean valid = false;
        ConnectionThread threadCon;

        try {
            con = dataSource.getConnection();
            threadCon = new ConnectionThread(con);
            threadCon.start();

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
        } catch (SQLException e) {
            if (e.getMessage().toLowerCase().contains("timeout")) {
               try {
                    throw new CustomException("Max connections reached! Wait a moment...", "SERVER BUSY");
                } catch (CustomException ex) {
                    Logger.getLogger(ImplementsBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("An error occurred.");
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
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }

        return valid;
    }

    /**
     * Modifies a user's password in the database.
     *
     * @param user the Profile whose password will be changed
     * @param newPassword the new password to set
     * @return true if the password was successfully modified, false otherwise
     * @throws CustomException if maximum connections are reached
     */
    @Override
    public boolean modifyPassword(Profile user, String newPassword) {
        Connection con = null;
        PreparedStatement stmt = null;
        boolean valid = false;
        ConnectionThread threadCon;

        try {
            con = dataSource.getConnection();
            threadCon = new ConnectionThread(con);
            threadCon.start();

            stmt = con.prepareStatement(SQLMODIFYPASSWD);
            stmt.setString(1, newPassword);
            stmt.setInt(2, user.getProfile_code());

            if (stmt.executeUpdate() > 0) {
                valid = true;
            }
        } catch (SQLException e) {
            if (e.getMessage().toLowerCase().contains("timeout")) {
                try {
                    throw new CustomException("Max connections reached! Wait a moment...", "SERVER BUSY");
                } catch (CustomException ex) {
                    Logger.getLogger(ImplementsBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("An error occurred.");
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing statement: " + e.getMessage());
            }
        }

        return valid;
    }

    /**
     * Deletes a user from the database.
     *
     * @param user the User object to be deleted
     * @return true if the user was successfully deleted, false otherwise
     * @throws CustomException if maximum connections are reached
     */
    @Override
    public boolean deleteUser(User user) {
        Connection con = null;
        PreparedStatement stmt = null;
        boolean valid = false;
        ConnectionThread threadCon;

        try {
            con = dataSource.getConnection();
            threadCon = new ConnectionThread(con);
            threadCon.start();
            stmt = con.prepareStatement(SQLDELETEUSER);
            stmt.setInt(1, user.getProfile_code());

            if (stmt.executeUpdate() > 0) {
                valid = true;
            }

        } catch (SQLException e) {
            if (e.getMessage().toLowerCase().contains("timeout")) {
                try {
                    throw new CustomException("Max connections reached! Wait a moment...", "SERVER BUSY");
                } catch (CustomException ex) {
                    Logger.getLogger(ImplementsBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("An error occurred.");
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
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }

        return valid;
    }

    /**
     * Inserts a new user into the database using a stored procedure.
     *
     * @param username the username for the new user
     * @param password the password for the new user
     * @return the created User profile if successful, null otherwise
     */
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
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
        return foundProfile;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return a HashMap containing all users with their profile codes as keys
     */
    @Override
    public HashMap<Integer, User> getAllUsers() {
        User user;
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
}