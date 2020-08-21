package ru.job4j.shortcut.model.requests;

/**
 * LoginPassReq
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.08.2020
 */
public class LoginPassReq {
    private String login;
    private String password;

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     * @return the login
     * @noinspection unused
     */
    public LoginPassReq setLogin(String login) {
        this.login = login;
        return this;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     * @return the password
     */
    public LoginPassReq setPassword(String password) {
        this.password = password;
        return this;
    }
}
