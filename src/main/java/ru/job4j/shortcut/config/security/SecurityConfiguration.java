package ru.job4j.shortcut.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SecurityConfiguration
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.08.2020
 */
@Configuration
@EnableConfigurationProperties
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private TokenService tokenService;

    /**
     * Instantiates a new Security configuration.
     *
     * @param tokenService the token service
     */
    @Autowired
    public SecurityConfiguration(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/redirect/**", "/authorization", "/registration")
                .permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .disable()
                .addFilterBefore(new JwtAuthenticationFilter(this.tokenService),
                UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    public void configure(AuthenticationManagerBuilder builder) {
        builder.getDefaultUserDetailsService();
    }
}
