package ru.job4j.shortcut.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Site
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.08.2020
 * @noinspection unused
 */
@Entity
@Table(name = "sites")
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    private String name;
    @OneToMany(mappedBy = "site")
    private List<Link> allLinks;

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     * @return the id
     */
    public Site setId(int id) {
        this.id = id;
        return this;
    }


    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     * @return the login
     */
    public Site setLogin(String login) {
        this.login = login;
        return this;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    @Column
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     * @return the password
     */
    public Site setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     * @return the name
     */
    public Site setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Gets all links.
     *
     * @return the all links
     */
    public List<Link> getAllLinks() {
        return allLinks;
    }

    /**
     * Sets all links.
     *
     * @param allLinks the all links
     * @return the all links
     */
    public Site setAllLinks(List<Link> allLinks) {
        this.allLinks = allLinks;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Site site = (Site) o;
        return id == site.id
                && Objects.equals(login, site.login)
                && Objects.equals(password, site.password)
                && Objects.equals(name, site.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, name);
    }
}
