package model;

/**
 * Represents a regular user in the system.
 * Extends the Profile class and adds user-specific properties
 * such as gender and card number.
 */
public class User extends Profile {

    private String gender;
    private String card_no;

    /**
     * Constructs a User with basic profile information and user-specific details.
     *
     * @param email the email address of the user
     * @param user_name the username for login
     * @param pssw the password for the account
     * @param telephone the telephone number
     * @param name the first name of the user
     * @param surname the last name of the user
     * @param gender the gender of the user
     * @param card_no the card number associated with the user
     */
    public User(String email, String user_name, String pssw, int telephone, String name, String surname, String gender, String card_no) {
        super(email, user_name, pssw, telephone, name, surname);
        this.gender = gender;
        this.card_no = card_no;
    }

    /**
     * Constructs a User with complete profile information including profile code.
     *
     * @param profile_code the unique identifier for the profile
     * @param email the email address of the user
     * @param user_name the username for login
     * @param pssw the password for the account
     * @param telephone the telephone number
     * @param name the first name of the user
     * @param surname the last name of the user
     * @param gender the gender of the user
     * @param card_no the card number associated with the user
     */
    public User(int profile_code, String email, String user_name, String pssw, int telephone, String name, String surname, String gender, String card_no) {
        super(profile_code, email, user_name, pssw, telephone, name, surname);
        this.gender = gender;
        this.card_no = card_no;
    }

    /**
     * Default constructor for User class.
     */
    public User() {
    }

    /**
     * Constructs a User with only login credentials.
     *
     * @param username the username for login
     * @param passwd the password for the account
     */
    public User(String username, String passwd) {
        super(username, passwd);
    }

    /**
     * Gets the gender of the user.
     *
     * @return the gender of the user
     */
    public String getGender() {
        return gender;
    }

    /**
     * Gets the card number of the user.
     *
     * @return the card number of the user
     */
    public String getCard_no() {
        return card_no;
    }

    /**
     * Sets the gender of the user.
     *
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Sets the card number of the user.
     *
     * @param card_no the card number to set
     */
    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    /**
     * Displays the user's complete information including profile details,
     * gender, and card number.
     */
    @Override
    public void mostrar() {
        System.out.println(super.toString() + "Gender: " + this.gender + "Card number: " + this.card_no);
    }
}