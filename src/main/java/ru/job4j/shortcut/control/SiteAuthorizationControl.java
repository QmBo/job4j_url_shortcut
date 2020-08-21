package ru.job4j.shortcut.control;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.shortcut.model.requests.LoginPassReq;
import ru.job4j.shortcut.service.MainLogic;

/**
 * SiteAuthorizationControl
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.08.2020
 */
@RestController
public class SiteAuthorizationControl {
    private final MainLogic mainLogic;

    /**
     * Instantiates a new Site authorization control.
     *
     * @param mainLogic the main logic
     */
    public SiteAuthorizationControl(MainLogic mainLogic) {
        this.mainLogic = mainLogic;
    }

    /**
     * Authorization string.
     *
     * @param loginPass the login pass
     * @return the string
     */
    @PostMapping(value = "/authorization", consumes = "application/json", produces = "application/json")
    public String authorization(@RequestBody LoginPassReq loginPass) {
        return this.mainLogic.authorization(loginPass);
    }
}
