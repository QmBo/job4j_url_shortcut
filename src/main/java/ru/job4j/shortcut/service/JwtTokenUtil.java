package ru.job4j.shortcut.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

/**
 * JwtTokenUtil
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.08.2020
 */
@Service
public class JwtTokenUtil {
    private final String secretKey;

    /**
     * Instantiates a new Jwt token util.
     *
     * @param secretKey the secret key
     */
    public JwtTokenUtil(@Value("${jwt.secret}")String secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * Gets jwt.
     *
     * @param line the line
     * @return the jwt
     */
    String getJWT(String line) {
        Key signingKey = this.getSigningKey();
        return Jwts.builder()
                .setSubject(line)
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .compact();
    }

    /**
     * Gets subject.
     *
     * @param jwt the jwt
     * @return the subject
     */
    String getSubject(String jwt) {
        return Jwts.parser()
                .setSigningKey(this.getSigningKey())
                .parseClaimsJws(jwt).getBody().getSubject();
    }

    private Key getSigningKey() {
        return new SecretKeySpec(
                Base64.getEncoder().encode(secretKey.getBytes()),
                SignatureAlgorithm.HS256.getJcaName()
        );
    }
}
