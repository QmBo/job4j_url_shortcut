package ru.job4j.shortcut.model.requests;

/**
 * UrlConvertReq
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.08.2020
 */
public class UrlConvertReq {
    private String url;
    private String link;

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url.
     *
     * @param url the url
     * @return the url
     * @noinspection unused
     */
    public UrlConvertReq setUrl(String url) {
        this.url = url;
        if (!url.isEmpty()) {
            this.link = url.substring(
                    url.indexOf("/", url.indexOf(".")) + 1
            );
        }
        return this;
    }

    /**
     * Gets link.
     *
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets link.
     *
     * @param link the link
     * @return the link
     * @noinspection unused
     */
    public UrlConvertReq setLink(String link) {
        this.link = link;
        return this;
    }

    @Override
    public String toString() {
        return "UrlConvertReq{"
                + "url='" + url + '\''
                + ", link='" + link + '\''
                + '}';
    }
}
