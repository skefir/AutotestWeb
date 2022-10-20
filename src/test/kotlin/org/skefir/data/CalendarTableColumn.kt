package org.skefir.data



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
    override fun getColumnByTitle(title: String): DataTableColumn = values().map{it.also { println("####${it.title} #-#${title}") }}.first { it.title == title }

}