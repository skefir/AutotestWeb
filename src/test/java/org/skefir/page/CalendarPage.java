package org.skefir.page;

import com.codeborne.selenide.Selenide;
import org.skefir.data.CalendarTableColumn;
import org.skefir.data.Currencies;
import org.skefir.data.DateFilterOptions;
import org.skefir.data.ImportanceFilterOption;
import org.skefir.elements.CalendarElements;
import org.skefir.elements.GroupingDataTable;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.util.EnumSet;
import java.util.Set;

/**
 * Класс работы сос страницей календаря
 */
@Slf4j
public class CalendarPage extends BasePage<CalendarPage> {

    protected static class CalendarPageElements extends Elements implements CalendarElements {
    }

    protected CalendarPageElements elementHelper = new CalendarPageElements();

    private final GroupingDataTable<CalendarTableColumn> calendarMainDataTable =
            new GroupingDataTable<>(elementHelper.getMainTable(), "ec-table", EnumSet.allOf(CalendarTableColumn.class));

    @Step("Устанавливаем фильтр валют значениями {0}")
    public CalendarPage setCurrenciesFilter(Set<Currencies> currenciesSet) {
        log.info("Устанавливаем фильтр валют значениями {}", currenciesSet);
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
    public CalendarEventInfoPage enterToEventByNumber(int eventNumber) {
        log.info("Заходим в событие с порядковым номером {}", eventNumber);
        calendarMainDataTable.getColumn(calendarMainDataTable.getRowByNumber(eventNumber), CalendarTableColumn.EVENT).$("a").click();
        return Selenide.page(CalendarEventInfoPage.class);
    }
}
