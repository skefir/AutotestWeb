package page;

import data.CalendarEventInfoTab;
import data.EventHistoryColumn;
import elements.CalendarEventInfoElements;
import elements.DataTable;
import elements.TabControl;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import util.DateUtils;

import java.time.LocalDate;
import java.util.EnumSet;

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

    @Step("Получаем значение столбца {1} в строке {0}")
    public String getColumn(int rowNumber, EventHistoryColumn column) {
        log.info("Получаем значение столбца {} в строке {}", column, rowNumber);
        return eventHistoryTable.getColumn(eventHistoryTable.getRowByNumber(rowNumber), column).getText();
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
