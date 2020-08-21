package ru.job4j.shortcut.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.DefaultToken;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.store.SiteRepository;

import java.util.concurrent.atomic.AtomicReference;

/**
 * UrlTokenService
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.08.2020
 */
@Service
public class UrlTokenService implements TokenService {
    private final SiteRepository repository;
    private final JwtTokenUtil jwtTokenUtil;

    /**
     * Instantiates a new Url token service.
     *
     * @param repository   the repository
     * @param jwtTokenUtil the jwt token util
     */
    @Autowired
    public UrlTokenService(SiteRepository repository, JwtTokenUtil jwtTokenUtil) {
        this.repository = repository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public Token allocateToken(String s) {
        return new DefaultToken(s, System.currentTimeMillis(), "");
    }

    @Override
    public Token verifyToken(String s) {
        AtomicReference<DefaultToken> result = new AtomicReference<>();
        String subject = this.jwtTokenUtil.getSubject(s);
        this.repository.findByName(subject)
                .ifPresentOrElse(site -> result.set(
                        new DefaultToken(
                                site.getName(),
                                System.currentTimeMillis(),
                                "")
                        ),
                        () -> result.set(new DefaultToken(
                                "",
                                System.currentTimeMillis(),
                                ""))
                );
        return result.get();
    }
}
