package model;

/**
 * Represents an Administrator user in the system.
 * Extends the Profile class and adds administrative-specific properties.
 */
public class Admin extends Profile {

    private String current_account;

    /**
     * Constructs a new Admin instance with the specified profile details.
     *
     * @param profile_code the unique identifier for the profile
     * @param email the email address of the admin
     * @param user_name the username for login
     * @param pssw the password for the account
     * @param telephone the telephone number
     * @param name the first name of the admin
     * @param surname the last name of the admin
     * @param current_account the current account information
     */
    public Admin(int profile_code, String email, String user_name, String pssw, int telephone, String name, String surname, String current_account) {

        super(profile_code, email, user_name, pssw, telephone, name, surname);
        this.current_account = current_account;
    }

    /**
     * Gets the current account information for this admin.
     *
     * @return the current account string
     */
    public String getCurrent_account() {
        return current_account;
    }

    /**
     * Sets the current account information for this admin.
     *
     * @param current_account the current account to set
     */
    public void setCurrent_account(String current_account) {
        this.current_account = current_account;
    }

    /**
     * Displays admin information. Currently not implemented.
     *
     * @throws UnsupportedOperationException as this method is not yet supported
     */
    @Override
    public void mostrar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}