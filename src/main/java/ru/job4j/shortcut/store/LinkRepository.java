package ru.job4j.shortcut.store;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.shortcut.model.Link;

import java.util.Optional;

/**
 * LinkRepository
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.08.2020
 */
public interface LinkRepository extends CrudRepository<Link, Integer> {
    /**
     * Find by short cut optional.
     *
     * @param shortCut the short cut
     * @return the optional
     */
    Optional<Link> findByShortCut(String shortCut);

    /**
     * Find by origin url optional.
     *
     * @param url the url
     * @return the optional
     */
    Optional<Link> findByOriginUrl(String url);

    /**
     * Add 1 visit to link by short cut.
     *
     * @param shortCut the short cut
     */
    @Transactional
    @Modifying
    @Query(value = "update Link l set l.requestsCount = l.requestsCount + 1 where l.shortCut = ?1")
    void updateRequestCountByShortCut(String shortCut);
}
