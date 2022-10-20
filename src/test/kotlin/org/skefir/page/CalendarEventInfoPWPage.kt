package org.skefir.page

import com.microsoft.playwright.Page
import io.qameta.allure.Step
import mu.KotlinLogging
import org.junit.jupiter.api.Assertions
import org.skefir.data.*
import org.skefir.elements.CalendarEventInfoPWElements
import org.skefir.elements.DataTablePW
import org.skefir.elements.TabControl
import org.skefir.util.DateUtils
import java.time.LocalDate
import java.util.*

private val log = KotlinLogging.logger {}

class CalendarEventInfoPWPage(page: Page) : BasePagePW<CalendarEventInfoPWPage>(page) {
    protected inner class CalendarEventInfoElements() : Elements(), CalendarEventInfoPWElements {
        override fun getPage(): Page {
            return page
        }
    }

    protected var eventInfoHelper = CalendarEventInfoElements()

    var tabControl = TabControl<CalendarEventInfoTab>(eventInfoHelper.getEventTabControl())

    private val eventHistoryTable = DataTablePW(
        eventInfoHelper.getEventHistoryTable(), "event-table-history", EnumSet.allOf(EventHistoryColumn::class.java)
    )

    @Step("Переходим на вкладку {0}")
    fun goToTab(tab: CalendarEventInfoTab): CalendarEventInfoPWPage {
        log.info("Переходим на вкладку {}", tab)
        tabControl.getTab(tab).click()
        return this
    }

    @Step("Проверяем что событие соответствует критериям {0}")
    fun checkEventInfo(eventFilteredCondition: EventFilteredCondition): CalendarEventInfoPWPage {
        log.info("Проверяем что событие соответствует критериям {}", eventFilteredCondition)
        checkImportance(eventFilteredCondition.importanceSet)
        checkCurrency(eventFilteredCondition.currenciesSet)
        checkDate(eventFilteredCondition.dateFilterOption)
        return this
    }

    @Step("Проверяем что событие имеет важность {0}")
    fun checkImportance(importanceSet: Set<ImportanceFilterOption>): CalendarEventInfoPWPage {
        log.info("Проверяем что событие имеет важность {}", importanceSet)
        Assertions.assertTrue(
            isOptionContains<ImportanceFilterOption>(importanceSet, eventInfoHelper.getEventImportance().textContent()),
            "Важность события ${
                eventInfoHelper.getEventImportance().textContent()
            } должна входить в выбранный фильтр ${importanceSet}"
        )
        return this
    }

    @Step("Проверяем что событие в валюте {0}")
    fun checkCurrency(currenciesSet: Set<Currencies>): CalendarEventInfoPWPage {
        log.info("Проверяем что событие в валюте {}", currenciesSet)
        Assertions.assertTrue(
            isOptionContains<Currencies>(currenciesSet, eventInfoHelper.getEventCurrency().textContent()),
            "Важность события ${
                eventInfoHelper.getEventCurrency().textContent()
            } должна входить в выбранный фильтр $currenciesSet"
        )
        return this
    }

    @Step("Проверяем что событие попадает в заданный интервал дат {0}")
    fun checkDate(dateFilterOptions: DateFilterOptions): CalendarEventInfoPWPage {
        log.info("Проверяем что событие попадает в заданный интервал дат {}", dateFilterOptions)
        val eventDate = DateUtils.convertDateTime(eventInfoHelper.getEventDate().textContent()).toLocalDate()
        Assertions.assertTrue(
            eventDate.isAfter(dateFilterOptions.beginPeriod)
                    && eventDate.isBefore(dateFilterOptions.finishPeriod),
            "Дата события ${eventDate} должна находиться в периоде ${dateFilterOptions}"
        )
        return this
    }


    @Step("Получаем содержимое таблицы истории")
    fun printHistoryToLog() {
        log.info("Получаем содержимое таблицы истории")
        val reportSet = EnumSet.of(
            EventHistoryColumn.DATE, EventHistoryColumn.ACTUAL,
            EventHistoryColumn.FORECAST, EventHistoryColumn.PREVIOUS
        )
        val header = String.format(
            "| %-25s             | %20s | %20s | %20s |",
            EventHistoryColumn.DATE.getTitle(),
            EventHistoryColumn.ACTUAL.getTitle(),
            EventHistoryColumn.FORECAST.getTitle(),
            EventHistoryColumn.PREVIOUS.getTitle()
        )
        val allureHistoryTable = StringBuilder(header)
        log.info(header)
        eventHistoryTable.getRowStream().map {
            eventHistoryTable.extractColumns(
                it,
                reportSet
            )
        }
            .takeWhile { e: Map<EventHistoryColumn, String> ->
                DateUtils.convertDate(
                    e[EventHistoryColumn.DATE]
                ).isAfter(LocalDate.now().minusMonths(12))
            }
            .forEach { e: Map<EventHistoryColumn, String> ->
                val rowValue = String.format(
                    "| %-25s             | %20s | %20s | %20s |",
                    e[EventHistoryColumn.DATE],
                    e[EventHistoryColumn.ACTUAL],
                    e[EventHistoryColumn.FORECAST],
                    e[EventHistoryColumn.PREVIOUS]
                )
                log.info(rowValue)
                allureHistoryTable.append("\n")
                allureHistoryTable.append(rowValue)
            }
        BasePage.addAllureTextAttacment("historyTable", allureHistoryTable.toString())
    }
}