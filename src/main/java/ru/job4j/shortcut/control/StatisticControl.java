package ru.job4j.shortcut.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.shortcut.service.MainLogic;

/**
 * StatisticControl
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.08.2020
 */
@RestController
public class StatisticControl {
    private final MainLogic mainLogic;

    /**
     * Instantiates a new Statistic control.
     *
     * @param mainLogic the main logic
     */
    @Autowired
    public StatisticControl(MainLogic mainLogic) {
        this.mainLogic = mainLogic;
    }

    /**
     * Return statistic string.
     *
     * @return the string
     */
    @GetMapping(value = "/statistic", produces = "application/json")
    public String returnStatistic() {
        return this.mainLogic.getStatistic();
    }
}
