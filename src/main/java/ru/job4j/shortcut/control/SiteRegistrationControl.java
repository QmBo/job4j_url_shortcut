package ru.job4j.shortcut.control;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.shortcut.service.MainLogic;
import ru.job4j.shortcut.model.requests.SiteReq;

/**
 * SiteRegistrationControl
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.08.2020
 */
@RestController
public class SiteRegistrationControl {
    private MainLogic logic;

    /**
     * Instantiates a new Site registration control.
     *
     * @param logic the logic
     */
    public SiteRegistrationControl(MainLogic logic) {
        this.logic = logic;
    }

    /**
     * Registration string.
     *
     * @param siteReq the site req
     * @return the string
     */
    @PostMapping(value = "/registration", consumes = "application/json", produces = "application/json")
    public String registration(@RequestBody SiteReq siteReq) {
        return this.logic.regSite(siteReq);
    }
}
