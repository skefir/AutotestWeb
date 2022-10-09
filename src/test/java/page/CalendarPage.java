package page;

import data.CalendarTableColumn;
import data.Currencies;
import data.DateFilterOptions;
import data.ImportanceFilterOption;
import elements.CalendarElements;
import elements.DataTable;
import elements.GropingDataTable;
import io.qameta.allure.Step;

import java.util.EnumSet;
import java.util.Set;

public class CalendarPage extends BasePage<CalendarPage> {

    protected static class CalendarPageElements extends Elements implements CalendarElements {
    }

    protected CalendarPageElements elementHelper = new CalendarPageElements();

    private final GropingDataTable<CalendarTableColumn> calendarMainDataTable =
            new GropingDataTable<>(elementHelper.getMainTable(), "ec-table", EnumSet.allOf(CalendarTableColumn.class));

    @Step("Устанавливаем фильтр валют занчениями {0}")
    public CalendarPage setCurrenciesFilter(Set<Currencies> currenciesSet) {
        return setFilterCheckboxGroup(elementHelper.getCurrenciesFilter(), currenciesSet);
    }

    @Step("Устанавливаем фильтр дат занчением {0}")
    public CalendarPage setDateFilter(DateFilterOptions dateFilterOptions) {
        return setFilterRadio(elementHelper.getDateFilter(), dateFilterOptions);
    }

    @Step("Устанавливаем фильтр важности занчениями {0}")
    public CalendarPage setImportanceFilter(Set<ImportanceFilterOption> currenciesSet) {
        return setFilterCheckboxGroup(elementHelper.getImportanceFilter(), currenciesSet);
    }

    @Step("Заходим в событие с порядковым номером {0}")
    public CalendarPage enterToEventByNumber(int eventNumber) {
        calendarMainDataTable.getColumn(calendarMainDataTable.getRowByNumber(eventNumber), CalendarTableColumn.EVENT).$("a").click();
        return getCurrentPage();
    }
}
