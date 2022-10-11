package org.skefir.data;

import lombok.Getter;

public enum EventHistoryColumn implements DataTableColumn {
    DATE("Date (GMT)"),
    REFERENCE("Reference"),
    ACTUAL("Actual"),
    FORECAST("Forecast"),
    PREVIOUS("Previous");

    @Getter
    private final String title;

    EventHistoryColumn(String title) {
        this.title = title;
    }
}
