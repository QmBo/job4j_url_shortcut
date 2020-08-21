package ru.job4j.shortcut.store;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.shortcut.model.Site;

import java.util.Optional;

/**
 * SiteRepository
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.08.2020
 */
public interface SiteRepository extends CrudRepository<Site, Integer> {
    /**
     * Find by name optional.
     *
     * @param name the name
     * @return the optional
     */
    Optional<Site> findByName(String name);

    /**
     * Find by login optional.
     *
     * @param login the login
     * @return the optional
     */
    Optional<Site> findByLogin(String login);
}
