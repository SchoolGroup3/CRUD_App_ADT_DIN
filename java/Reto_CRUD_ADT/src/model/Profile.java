package model;

/**
 * Abstract class representing a user profile in the system.
 * Serves as the base class for different types of user profiles
 * and defines common properties and behaviors for all profiles.
 */
public abstract class Profile {

    private int profile_code;
    private String email;
    private String user_name;
    private String pssw;
    private int telephone;
    private String name;
    private String surname;

    /**
     * Default constructor for Profile class.
     */
    public Profile() {
    }

    /**
     * Constructs a Profile with basic user information.
     *
     * @param email the email address of the user
     * @param user_name the username for login
     * @param pssw the password for the account
     * @param telephone the telephone number
     * @param name the first name of the user
     * @param surname the last name of the user
     */
    public Profile(String email, String user_name, String pssw, int telephone, String name, String surname) {
        this.email = email;
        this.user_name = user_name;
        this.pssw = pssw;
        this.telephone = telephone;
        this.name = name;
        this.surname = surname;
    }

    /**
     * Constructs a Profile with complete user information including profile code.
     *
     * @param profile_code the unique identifier for the profile
     * @param email the email address of the user
     * @param user_name the username for login
     * @param pssw the password for the account
     * @param telephone the telephone number
     * @param name the first name of the user
     * @param surname the last name of the user
     */
    public Profile(int profile_code, String email, String user_name, String pssw, int telephone, String name, String surname) {
        this.profile_code = profile_code;
        this.email = email;
        this.user_name = user_name;
        this.pssw = pssw;
        this.telephone = telephone;
        this.name = name;
        this.surname = surname;
    }

    /**
     * Constructs a Profile with only login credentials.
     *
     * @param user_name the username for login
     * @param pssw the password for the account
     */
    public Profile(String user_name, String pssw) {
        this.user_name = user_name;
        this.pssw = pssw;
    }

    /**
     * Gets the unique profile code.
     *
     * @return the profile code
     */
    public int getProfile_code() {
        return profile_code;
    }

    /**
     * Gets the email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPssw() {
        return pssw;
    }

    /**
     * Gets the telephone number.
     *
     * @return the telephone number
     */
    public int getTelephone() {
        return telephone;
    }

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the unique profile code.
     *
     * @param profile_code the profile code to set
     */
    public void setProfile_code(int profile_code) {
        this.profile_code = profile_code;
    }

    /**
     * Sets the email address.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the username.
     *
     * @param user_name the username to set
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /**
     * Sets the password.
     *
     * @param pssw the password to set
     */
    public void setPssw(String pssw) {
        this.pssw = pssw;
    }

    /**
     * Sets the telephone number.
     *
     * @param telephone the telephone number to set
     */
    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    /**
     * Sets the first name.
     *
     * @param name the first name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the last name.
     *
     * @param surname the last name to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Returns a string representation of the profile.
     *
     * @return a string containing all profile information
     */
    @Override
    public String toString() {
        return "profile_code=" + profile_code + ", email=" + email + ", user_name=" + user_name + ", pssw=" + pssw + ", telephone=" + telephone + ", name=" + name + ", surname=" + surname + '}';
    }

    /**
     * Abstract method to display profile information.
     * Must be implemented by concrete subclasses.
     */
    public abstract void mostrar();
}