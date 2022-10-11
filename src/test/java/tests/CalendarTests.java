package tests;

import com.codeborne.selenide.junit5.ScreenShooterExtension;
import config.BaseTestExtension;
import data.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import page.CalendarEventInfoPage;
import page.CalendarPage;

import java.util.EnumSet;

@Slf4j

@ExtendWith({BaseTestExtension.class})
public class CalendarTests {

    @RegisterExtension
    static ScreenShooterExtension screenshotEmAll = new ScreenShooterExtension(true).to("target/screenshots");

    private final CalendarPage calendarPage = new CalendarPage();

    private final CalendarEventInfoPage calendarEventInfoPage = new CalendarEventInfoPage();

    @Test
    public void expTest() {
        final EventFilteredCondition eventFilteredCondition =
                new EventFilteredCondition(EnumSet.of(ImportanceFilterOption.MEDIUM),
                        DateFilterOptions.CURRENT_MONTH, EnumSet.of(Currencies.CHF));

        calendarPage.setCurrenciesFilter(eventFilteredCondition.getCurrenciesSet())
                .setDateFilter(eventFilteredCondition.getDateFilterOption())
                .setImportanceFilter(eventFilteredCondition.getImportanceSet())
                .enterToEventByNumber(1);
        calendarEventInfoPage.goToTab(CalendarEventInfoTab.HISTORY)
                .printHistoryToLog();

    }
}
