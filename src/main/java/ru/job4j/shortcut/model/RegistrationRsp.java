package ru.job4j.shortcut.model;

import java.util.StringJoiner;

import static java.lang.String.*;

/**
 * RegistrationRsp
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.08.2020
 */
public class RegistrationRsp {
    private boolean registration;
    private String login;
    private String password;

    /**
     * Sets registration.
     *
     * @param registration the registration
     * @return the registration
     * @noinspection UnusedReturnValue
     */
    public RegistrationRsp setRegistration(boolean registration) {
        this.registration = registration;
        return this;
    }

    /**
     * Sets login.
     *
     * @param login the login
     * @return the login
     */
    public RegistrationRsp setLogin(String login) {
        this.login = login;
        return this;
    }


    /**
     * Sets password.
     *
     * @param password the password
     * @return the password
     */
    public RegistrationRsp setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJSON() {
        return new StringJoiner(",", "{", "}")
                .add(format("\"registration\":%s", this.registration))
                .add(format("\"login\":\"%s\"", this.login == null ? "" : this.login))
                .add(format("\"password\":\"%s\"", this.password == null ? "" : this.password))
                .toString();
    }

    @Override
    public String toString() {
        return "RegistrationRsp{"
                + "registration=" + registration
                + ", login='" + login + '\''
                + ", password='" + password + '\''
                + '}';
    }
}
