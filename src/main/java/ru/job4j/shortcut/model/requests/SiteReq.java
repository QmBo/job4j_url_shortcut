package ru.job4j.shortcut.model.requests;

/**
 * SiteReq
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.08.2020
 */
public class SiteReq {
    private String site;

    /**
     * Gets site.
     *
     * @return the site
     */
    public String getSite() {
        return site;
    }

    /**
     * Sets site.
     *
     * @param site the site
     * @return the site
     */
    public SiteReq setSite(String site) {
        this.site = site;
        return this;
    }

    @Override
    public String toString() {
        return "SiteReq{"
                + "site='" + site + '\''
                + '}';
    }
}
