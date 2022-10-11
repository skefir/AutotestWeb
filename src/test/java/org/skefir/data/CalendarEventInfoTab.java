package org.skefir.data;

import lombok.Getter;

public enum CalendarEventInfoTab implements ControlTabEntity {
    OVERVIEW("Overview"),
    CHART("Chart"),
    HISTORY("History"),
    WIDGET("Widget");

    @Getter
    private final String title;

    CalendarEventInfoTab(String title) {
        this.title = title;
    }
}
