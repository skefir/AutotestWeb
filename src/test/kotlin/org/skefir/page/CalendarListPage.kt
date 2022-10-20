package org.skefir.page

import com.microsoft.playwright.Page
import io.qameta.allure.Step
import org.skefir.data.Currencies
import org.skefir.elements.CalendarListElements


class CalendarListPage(page: Page) : BasePagePW<CalendarListPage>(page) {
    protected inner class CalendarPageElements() : Elements(), CalendarListElements {
        override fun getPage(): Page {
            return page
        }
    }

    protected var calendarHelper = CalendarPageElements()

//    private val calendarMainDataTable: GroupingDataTable<CalendarTableColumn> = GroupingDataTable(
//        elementHelper.getMainTable(), "ec-table", EnumSet.allOf(
//            CalendarTableColumn::class.java
//        )
//    )

    @Step("Устанавливаем фильтр валют значениями {0}")
    fun setCurrenciesFilter(currenciesSet: Set<Currencies>): CalendarListPage {
//        log.info("Устанавливаем фильтр валют значениями {}", currenciesSet)
        return setFilterCheckboxGroup(calendarHelper.getCurrenciesFilter(), currenciesSet)
    }
//
//    @Step("Set date filter with {0}")
//    fun setDateFilter(dateFilterOptions: DateFilterOptions): CalendarPage? {
////        log.info("Устанавливаем фильтр валют занчениями {}", dateFilterOptions)
//        return setFilterRadio(calendarHelper.getDateFilter(), dateFilterOptions)
//    }
//
//    @Step("Устанавливаем фильтр важности занчениями {0}")
//    fun setImportanceFilter(currenciesSet: Set<ImportanceFilterOption?>?): CalendarPage? {
//        log.info("Устанавливаем фильтр важности занчениями {}", currenciesSet)
//        return setFilterCheckboxGroup(calendarHelper.getImportanceFilter(), currenciesSet)
//    }
//
//    @Step("Заходим в событие с порядковым номером {0}")
//    fun enterToEventByNumber(eventNumber: Int): CalendarEventInfoPage {
//        log.info("Заходим в событие с порядковым номером {}", eventNumber)
//        calendarMainDataTable.getColumn(calendarMainDataTable.getRowByNumber(eventNumber), CalendarTableColumn.EVENT)
//            .`$`("a").click()
//        return Selenide.page(CalendarEventInfoPage::class.java)
//    }
}