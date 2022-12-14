package org.skefir.elements;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public interface CalendarEventInfoElements {

    default SelenideElement getEventRoot() {
        return Selenide.$("#eventContentPanel");
    }

    default SelenideElement getEventCurrency() {
        return getEventRoot().$(".economic-calendar__event-header-currency");
    }

    default SelenideElement getEventImportance() {
        return getEventRoot().$(".event-table__importance");
    }

    default SelenideElement getEventDate() {
        return getEventRoot().$("td.event-table__date");
    }

    default SelenideElement getEventTabControl() {
        return getEventRoot().$("ul#calendar-tabs");
    }

    default SelenideElement getEventHistoryTable() {
        return getEventRoot().$("#tab_content_history");
    }


}
