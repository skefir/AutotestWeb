package org.skefir.page;

import org.skefir.data.*;
import org.skefir.elements.CalendarEventInfoElements;
import org.skefir.elements.DataTable;
import org.skefir.elements.TabControl;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.skefir.util.DateUtils;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class CalendarEventInfoPage extends BasePage<CalendarEventInfoPage> {
    protected static class CalendarEventInfoPageElements extends Elements implements CalendarEventInfoElements {
    }

    protected CalendarEventInfoPageElements elementHelper = new CalendarEventInfoPageElements();

    TabControl<CalendarEventInfoTab> tabControl = new TabControl<>(elementHelper.getEventTabControl());

    private final DataTable<EventHistoryColumn> eventHistoryTable =
            new DataTable<>(elementHelper.getEventHistoryTable(), "event-table-history"
                    , EnumSet.allOf(EventHistoryColumn.class));

    @Step("Переходим на вкладку {0}")
    public CalendarEventInfoPage goToTab(CalendarEventInfoTab tab) {
        log.info("Переходим на вкладку {}", tab);
        tabControl.getTab(tab).click();
        return this;
    }

    @Step("Проверяем что событие соответствует критериям {0}")
    public CalendarEventInfoPage checkEventInfo(EventFilteredCondition eventFilteredCondition) {
        log.info("Проверяем что событие соответствует критериям {}", eventFilteredCondition);
        checkImportance(eventFilteredCondition.getImportanceSet());
        checkCurrency(eventFilteredCondition.getCurrenciesSet());
        checkDate(eventFilteredCondition.getDateFilterOption());
        return this;
    }

    @Step("Проверяем что событие имеет важность {0}")
    public CalendarEventInfoPage checkImportance(Set<ImportanceFilterOption> importanceSet) {
        log.info("Проверяем что событие имеет важность {}", importanceSet);
        assertTrue(isOptionContains(importanceSet, elementHelper.getEventImportance().getText()),
                String.format("Важность события %s должна входить в выбранный фильтр %s"
                        , elementHelper.getEventImportance().getText(), importanceSet));

        return this;
    }

    @Step("Проверяем что событие в валюте {0}")
    public CalendarEventInfoPage checkCurrency(Set<Currencies> currenciesSet) {
        log.info("Проверяем что событие в валюте {}", currenciesSet);
        assertTrue(isOptionContains(currenciesSet, elementHelper.getEventCurrency().getText()),
                String.format("Важность события %s должна входить в выбранный фильтр %s"
                        , elementHelper.getEventCurrency().getText(), currenciesSet));

        return this;
    }

    @Step("Проверяем что событие попадает в заданный интервал дат {0}")
    public CalendarEventInfoPage checkDate(DateFilterOptions dateFilterOptions) {
        log.info("Проверяем что событие попадает в заданный интервал дат {}", dateFilterOptions);
        LocalDate eventDate = DateUtils.convertDateTime(elementHelper.getEventDate().getText()).toLocalDate();
        assertTrue(eventDate.isAfter(dateFilterOptions.getBeginPeriod())
                        && eventDate.isBefore(dateFilterOptions.getFinishPeriod())
                , String.format("Дата события %s должна находиться в периоде %s", eventDate, dateFilterOptions));
        return this;
    }


    @Step("Получаем содержимое таблицы истории")
    public void printHistoryToLog() {
        log.info("Получаем содержимое таблицы истории");
        EnumSet<EventHistoryColumn> reportSet = EnumSet.of(EventHistoryColumn.DATE, EventHistoryColumn.ACTUAL,
                EventHistoryColumn.FORECAST, EventHistoryColumn.PREVIOUS);

        String header = String.format("| %-25s             | %20s | %20s | %20s |", EventHistoryColumn.DATE.getTitle(),
                EventHistoryColumn.ACTUAL.getTitle(), EventHistoryColumn.FORECAST.getTitle()
                , EventHistoryColumn.PREVIOUS.getTitle());
        StringBuilder allureHistoryTable = new StringBuilder(header);
        log.info(header);
        eventHistoryTable.getRowStream().map(e -> eventHistoryTable.extractColumns(e, reportSet))
                .takeWhile(e -> DateUtils.convertDate(e.get(EventHistoryColumn.DATE)).isAfter(LocalDate.now().minusMonths(12)))
                .forEach(e -> {
                    String rowValue = String.format("| %-25s             | %20s | %20s | %20s |"
                            , e.get(EventHistoryColumn.DATE), e.get(EventHistoryColumn.ACTUAL)
                            , e.get(EventHistoryColumn.FORECAST)
                            , e.get(EventHistoryColumn.PREVIOUS));
                    log.info(rowValue);

                    allureHistoryTable.append("\n");
                    allureHistoryTable.append(rowValue);
                });
        addAllureDescription("historyTable", allureHistoryTable.toString());
    }


}
