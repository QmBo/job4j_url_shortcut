package ru.job4j.shortcut.control;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.shortcut.model.requests.UrlConvertReq;
import ru.job4j.shortcut.service.MainLogic;

/**
 * UrlRegistrationControl
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.08.2020
 */
@RestController
public class UrlRegistrationControl {
    private final MainLogic mainLogic;

    /**
     * Instantiates a new Url registration control.
     *
     * @param mainLogic the main logic
     */
    public UrlRegistrationControl(MainLogic mainLogic) {
        this.mainLogic = mainLogic;
    }

    /**
     * Convert url string.
     *
     * @param convertRequest the convert request
     * @return the string
     */
    @PostMapping(value = "/convert", produces = "application/json", consumes = "application/json")
    public String convertUrl(@RequestBody UrlConvertReq convertRequest) {
        return this.mainLogic.getShortCut(convertRequest);
    }
}
