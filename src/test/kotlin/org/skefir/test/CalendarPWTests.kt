package org.skefir.test

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.skefir.conf.BaseTestPWExtension
import org.skefir.conf.TestConfig
import org.skefir.data.Currencies
import org.skefir.data.DateFilterOptions
import org.skefir.data.ImportanceFilterOption
import org.skefir.entity.EventFilteredCondition
import org.skefir.page.CalendarListPage
import java.util.*

@ExtendWith(BaseTestPWExtension::class)
class CalendarPWTests {

    @Test
    fun expTest() {
        val eventFilteredCondition = EventFilteredCondition(
            EnumSet.of(ImportanceFilterOption.MEDIUM),
            DateFilterOptions.CURRENT_MONTH, EnumSet.of(Currencies.CHF)
        )
        val browser = TestConfig.getBrowser()
        val page = browser.newPage()
        page.navigate(TestConfig.configProperties.getString("urls.metaUrl"))
        val calndarPage = CalendarListPage(page)
        calndarPage.setCurrenciesFilter(eventFilteredCondition.currenciesSet)
    }
}