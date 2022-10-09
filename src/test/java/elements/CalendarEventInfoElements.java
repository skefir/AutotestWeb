package elements;

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

    default SelenideElement getEventTabControl() {
        return getEventRoot().$("ul#calendar-tabs");
    }


}
