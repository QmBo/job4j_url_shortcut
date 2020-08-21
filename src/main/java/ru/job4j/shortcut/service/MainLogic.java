package ru.job4j.shortcut.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.model.*;
import ru.job4j.shortcut.model.requests.LoginPassReq;
import ru.job4j.shortcut.model.requests.SiteReq;
import ru.job4j.shortcut.model.requests.UrlConvertReq;
import ru.job4j.shortcut.store.LinkRepository;
import ru.job4j.shortcut.store.SiteRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

import static java.lang.String.format;
import static java.util.UUID.nameUUIDFromBytes;
import static java.util.UUID.randomUUID;

/**
 * MainLogic
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.08.2020
 */
@Service
public class MainLogic {
    private final int length;
    private final SiteRepository sites;
    private final JwtTokenUtil jwtBuilder;
    private final LinkRepository linkRepository;

    /**
     * Instantiates a new Main logic.
     *
     * @param sites          the sites
     * @param jwtBuilder     the jwt builder
     * @param linkRepository the link repository
     * @param length         the length of short cut
     */
    public MainLogic(final SiteRepository sites, final JwtTokenUtil jwtBuilder,
                     final LinkRepository linkRepository, @Value("${alias.length}") final int length) {
        this.sites = sites;
        this.jwtBuilder = jwtBuilder;
        this.length = length;
        this.linkRepository = linkRepository;
    }

    /**
     * Site registration.
     *
     * @param siteReq the site req with site name
     * @return login password or false
     */
    public String regSite(final SiteReq siteReq) {
        RegistrationRsp result =  new RegistrationRsp();
        if (siteReq != null && !siteReq.getSite().isEmpty()) {
            String name = siteReq.getSite();
            if (!this.sites.findByName(name).isPresent()) {
                String login = nameUUIDFromBytes(name.getBytes()).toString();
                String password = randomUUID().toString();
                this.sites.save(new Site()
                        .setLogin(login)
                        .setName(name)
                        .setPassword(this.passwordEncoder().encode(password))
                );
                result.setLogin(login).setPassword(password).setRegistration(true);
            }
        }
        return result.toJSON();
    }

    /**
     * Gets short cut.
     *
     * @param urlConvertRequest the url convert request
     * @return the short cut
     */
    public String getShortCut(final UrlConvertReq urlConvertRequest) {
        final String[] result = new String[1];
        this.linkRepository.findByOriginUrl(urlConvertRequest.getUrl())
                .ifPresentOrElse(
                        link -> result[0] = this.getJSON(link.getShortCut()),
                        () -> {
                            String shortCut = RandomStringUtils.randomAlphanumeric(length);
                            result[0] = this.getJSON(shortCut);
                            this.linkRepository.save(
                                    new Link()
                                            .setOriginUrl(urlConvertRequest.getUrl())
                                            .setShortCut(shortCut)
                                            .setSite(
                                                    this.sites.findByName(
                                                            SecurityContextHolder
                                                                    .getContext()
                                                                    .getAuthentication()
                                                                    .getName()
                                                    ).orElse(new Site())
                                            )
                            );
                        }
                );
        return result[0];
    }

    /**
     * Site authorization.
     *
     * @param capture the capture
     * @return authorization code
     */
    public String authorization(final LoginPassReq capture) {
        String result = "";
        if (capture != null
                && capture.getLogin() != null
                && capture.getPassword() != null
                && !capture.getPassword().isEmpty()
                && !capture.getLogin().isEmpty()) {
            Site site = this.sites.findByLogin(capture.getLogin()).orElse(new Site().setPassword(""));
            if (this.passwordEncoder().matches(capture.getPassword(), site.getPassword())) {
                String key = this.jwtBuilder.getJWT(site.getName());
                result = this.getJSON(key);
            }
        }
        return result;
    }

    private String getJSON(final String key) {
        String result;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", key);
        result = jsonObject.toString();
        return result;
    }


    /**
     * Redirect of short cut.
     *
     * @param key the short cut key
     * @return redirect
     */
    public String redirect(final String key) {
        final String[] result = {"redirect:/urlNotExist"};
        this.linkRepository.findByShortCut(key)
                .ifPresent(link -> {
                    result[0] = format("redirect:%s", link.getOriginUrl());
                    this.linkRepository.save(link.visit());
                });
        return result[0];
    }

    /**
     * Gets statistic of site.
     *
     * @return the statistic
     */
    public String getStatistic() {
        String result = "";
        List<Link> links = new LinkedList<>();
        this.sites.findByName(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        ).ifPresent(site -> links.addAll(site.getAllLinks()));
        if (!links.isEmpty()) {
            StringJoiner joiner = new StringJoiner(",", "{", "}");
            links.forEach(link ->
                joiner.add(format("{\"url\":\"%s\", \"total\":%s}", link.getOriginUrl(), link.getRequestsCount()))
            );
            result = joiner.toString();
        }
        return result;
    }

    /**
     * Password encoder password encoder.
     *
     * @return the password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
