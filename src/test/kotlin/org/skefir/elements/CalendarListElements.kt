package org.skefir.elements

import com.microsoft.playwright.Locator

interface CalendarListElements : PlaywrightPage {
    fun getCalendarRoot(): Locator {
        return  getPage().locator("#calendarContainer")
    }

    fun getFilterArea(): Locator {
        return getCalendarRoot().locator("#economicCalendarFilter")
    }

    fun getCurrenciesFilter(): Locator {
        return getFilterArea().locator("ul#economicCalendarFilterCurrency")
    }

    fun getDateFilter(): Locator {
        return getFilterArea().locator("ul#economicCalendarFilterDate")
    }

    fun getImportanceFilter(): Locator {
        return getFilterArea().locator("ul#economicCalendarFilterImportance")
    }

    fun getMainTable(): Locator {
        return getCalendarRoot().locator(".//div[@class='ec-table' and .//div[@id='economicCalendarTable']]")
    }
}