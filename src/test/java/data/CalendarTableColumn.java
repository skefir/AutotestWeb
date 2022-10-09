package data;

import lombok.Getter;

public enum CalendarTableColumn implements DataTableColumn<CalendarTableColumn> {
    TIME(    "Time,"),
    CURRENCY("Currency"),
    EVENT(   "Event"),
    ACTUAL(  "Actual"),
    FORECAST("Forecast"),
    PREVIOUS("Previous");

    @Getter
    final String title;

    CalendarTableColumn(String title) {
        this.title = title;
    }
}
