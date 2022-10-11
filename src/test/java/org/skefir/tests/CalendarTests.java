package org.skefir.tests;

import org.skefir.config.BaseTestExtension;
import org.skefir.data.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skefir.page.CalendarPage;

import java.util.EnumSet;

@Slf4j

@ExtendWith({BaseTestExtension.class})
public class CalendarTests {

    @Test
    public void expTest() {
        final EventFilteredCondition eventFilteredCondition =
                new EventFilteredCondition(EnumSet.of(ImportanceFilterOption.MEDIUM),
                        DateFilterOptions.CURRENT_MONTH, EnumSet.of(Currencies.CHF));
        final CalendarPage calendarPage = new CalendarPage();
        calendarPage.setCurrenciesFilter(eventFilteredCondition.getCurrenciesSet())
                .setDateFilter(eventFilteredCondition.getDateFilterOption())
                .setImportanceFilter(eventFilteredCondition.getImportanceSet())
                .enterToEventByNumber(1)
                .checkEventInfo(eventFilteredCondition)
                .goToTab(CalendarEventInfoTab.HISTORY)
                .printHistoryToLog();
    }
}
