package org.skefir.elements;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public interface CalendarElements {
    default  SelenideElement getCalendarRoot() {
        return Selenide.$("#calendarContainer");
    }
    default SelenideElement getFilterArea() {
        return getCalendarRoot().$("#economicCalendarFilter");
    }

    default SelenideElement getCurrenciesFilter() {
        return getFilterArea().$("ul#economicCalendarFilterCurrency");
    }
    default SelenideElement getDateFilter() {
        return getFilterArea().$("ul#economicCalendarFilterDate");
    }
    default SelenideElement getImportanceFilter() {
        return getFilterArea().$("ul#economicCalendarFilterImportance");
    }

    default SelenideElement getMainTable() {
        return getCalendarRoot().$x(".//div[@class='ec-table' and .//div[@id='economicCalendarTable']]");
    }
}
