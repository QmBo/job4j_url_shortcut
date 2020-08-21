package ru.job4j.shortcut.config.security;

import com.sun.security.auth.UserPrincipal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * JwtAuthenticationFilter
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.08.2020
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final TokenService tokenService;

    /**
     * Instantiates a new Jwt authentication filter.
     *
     * @param tokenService the token service
     */
    public JwtAuthenticationFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        String authHeader = req.getHeader("Authorization");
        if (!(authHeader == null || !authHeader.startsWith("Bearer "))) {
            UsernamePasswordAuthenticationToken token = createToken(authHeader);
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        chain.doFilter(req, resp);
    }

    private UsernamePasswordAuthenticationToken createToken(String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        UserPrincipal userPrincipal = new UserPrincipal(this.tokenService.verifyToken(token).getKey());
        return new UsernamePasswordAuthenticationToken(userPrincipal, null, Collections.emptyList());
    }
}