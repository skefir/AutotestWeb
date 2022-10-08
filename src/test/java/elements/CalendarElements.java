package elements;

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
}
