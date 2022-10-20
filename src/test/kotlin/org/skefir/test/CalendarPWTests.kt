package org.skefir.test

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.skefir.conf.BaseTestPWExtension
import org.skefir.conf.TestConfig
import org.skefir.data.*
import org.skefir.page.CalendarListPage
import java.util.*

@ExtendWith(BaseTestPWExtension::class)
class CalendarPWTests {

    @Test

    fun calendarFilterTest() {
        val eventFilteredCondition = EventFilteredCondition(
            EnumSet.of(ImportanceFilterOption.MEDIUM),
            DateFilterOptions.CURRENT_MONTH, EnumSet.of(Currencies.CHF)
        )
        val browser = TestConfig.getBrowser()
        val page = browser.newPage()
        page.navigate(TestConfig.configProperties.getString("urls.metaUrl"))
        val calendarPage = CalendarListPage(page)
        calendarPage.setCurrenciesFilter(eventFilteredCondition.currenciesSet)
            .setDateFilter(eventFilteredCondition.dateFilterOption)
            .setImportanceFilter(eventFilteredCondition.importanceSet)
            .enterToEventByNumber(1)
            .checkEventInfo(eventFilteredCondition)
            .goToTab(CalendarEventInfoTab.HISTORY)
            .printHistoryToLog()
    }
}