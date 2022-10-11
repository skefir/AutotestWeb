package page;

import data.CalendarTableColumn;
import data.Currencies;
import data.DateFilterOptions;
import data.ImportanceFilterOption;
import elements.CalendarElements;
import elements.GropingDataTable;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.util.EnumSet;
import java.util.Set;

@Slf4j
public class CalendarPage extends BasePage<CalendarPage> {

    protected static class CalendarPageElements extends Elements implements CalendarElements {
    }

    protected CalendarPageElements elementHelper = new CalendarPageElements();

    private final GropingDataTable<CalendarTableColumn> calendarMainDataTable =
            new GropingDataTable<>(elementHelper.getMainTable(), "ec-table", EnumSet.allOf(CalendarTableColumn.class));

    @Step("Устанавливаем фильтр валют занчениями {0}")
    public CalendarPage setCurrenciesFilter(Set<Currencies> currenciesSet) {
        log.info("Устанавливаем фильтр валют занчениями {}", currenciesSet);
        return setFilterCheckboxGroup(elementHelper.getCurrenciesFilter(), currenciesSet);
    }



    @Step("Set date filter with {0}")
    public CalendarPage setDateFilter(DateFilterOptions dateFilterOptions) {
        log.info("Устанавливаем фильтр валют занчениями {}", dateFilterOptions);
        return setFilterRadio(elementHelper.getDateFilter(), dateFilterOptions);
    }

    @Step("Устанавливаем фильтр важности занчениями {0}")
    public CalendarPage setImportanceFilter(Set<ImportanceFilterOption> currenciesSet) {
        log.info("Устанавливаем фильтр важности занчениями {}", currenciesSet);
        return setFilterCheckboxGroup(elementHelper.getImportanceFilter(), currenciesSet);
    }

    @Step("Заходим в событие с порядковым номером {0}")
    public CalendarPage enterToEventByNumber(int eventNumber) {
        log.info("Заходим в событие с порядковым номером {}", eventNumber);
        calendarMainDataTable.getColumn(calendarMainDataTable.getRowByNumber(eventNumber), CalendarTableColumn.EVENT).$("a").click();
        return getCurrentPage();
    }
}
