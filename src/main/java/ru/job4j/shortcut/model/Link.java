package ru.job4j.shortcut.model;

import javax.persistence.*;

/**
 * Link
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.08.2020
 */
@Entity
@Table(name = "links")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String originUrl;
    private String shortCut;
    private int requestsCount;
    @ManyToOne
    @JoinColumn(name = "site_id")
    private Site site;

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
    public Link setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Gets origin url.
     *
     * @return the origin url
     */
    @Column
    public String getOriginUrl() {
        return originUrl;
    }

    /**
     * Sets origin url.
     *
     * @param originUrl the origin url
     * @return the origin url
     */
    public Link setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
        return this;
    }

    /**
     * Gets short cut.
     *
     * @return the short cut
     */
    @Column
    public String getShortCut() {
        return shortCut;
    }

    /**
     * Sets short cut.
     *
     * @param shortCut the short cut
     * @return the short cut
     */
    public Link setShortCut(String shortCut) {
        this.shortCut = shortCut;
        return this;
    }

    /**
     * Gets requests count.
     *
     * @return the requests count
     */
    @Column
    public int getRequestsCount() {
        return requestsCount;
    }

    /**
     * Sets requests count.
     *
     * @param requestsCount the requests count
     * @return the requests count
     */
    public Link setRequestsCount(int requestsCount) {
        this.requestsCount = requestsCount;
        return this;
    }

    /**
     * Gets site.
     *
     * @return the site
     */
    public Site getSite() {
        return site;
    }

    /**
     * Sets site.
     *
     * @param site the site
     * @return the site
     */
    public Link setSite(Site site) {
        this.site = site;
        return this;
    }

    /**
     * Visit link.
     *
     * @return the link
     */
    public Link visit() {
        this.requestsCount++;
        return this;
    }

    @Override
    public String toString() {
        return "Link{"
                + "id=" + id
                + ", originUrl='" + originUrl + '\''
                + ", shortCut='" + shortCut + '\''
                + ", requestsCount=" + requestsCount
                + ", site=" + site
                + '}';
    }
}
