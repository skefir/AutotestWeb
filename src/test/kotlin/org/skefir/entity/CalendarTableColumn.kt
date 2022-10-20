package org.skefir.entity



enum class CalendarTableColumn(private val title: String) : DataTableColumn {
    TIME(    "Time,"),
    CURRENCY("Currency"),
    EVENT(   "Event"),
    ACTUAL(  "Actual"),
    FORECAST("Forecast"),
    PREVIOUS("Previous");

    override fun getTitle(): String {
        return title
    }
    override fun getColumnByTitle(title: String): DataTableColumn {
        return CalendarTableColumn.values().filter { it.title == title }.first()
    }
}