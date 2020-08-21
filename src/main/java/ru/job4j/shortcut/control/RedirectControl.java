package ru.job4j.shortcut.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.job4j.shortcut.service.MainLogic;

/**
 * RedirectControl
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.08.2020
 */
@Controller
public class RedirectControl {
    private final MainLogic mainLogic;

    /**
     * Instantiates a new Redirect control.
     *
     * @param mainLogic the main logic
     */
    public RedirectControl(MainLogic mainLogic) {
        this.mainLogic = mainLogic;
    }

    /**
     * Redirect string.
     *
     * @param key the key
     * @return the string
     */
    @GetMapping(value = "/redirect/{key}")
    public String redirect(@PathVariable String key) {
        return this.mainLogic.redirect(key);
    }
}
