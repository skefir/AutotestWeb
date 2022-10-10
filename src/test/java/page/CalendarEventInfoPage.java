package page;

import com.codeborne.selenide.SelenideElement;
import data.CalendarEventInfoTab;
import data.EventHistoryColumn;
import elements.CalendarEventInfoElements;
import elements.DataTable;
import elements.TabControl;
import io.qameta.allure.Step;

import java.util.EnumSet;
import java.util.stream.Collectors;

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
        tabControl.getTab(tab).click();
        return this;
    }

    @Step("Получаем значение столбца {1} в строке {0}")
    public String getColumn(int rowNumber, EventHistoryColumn column) {
        return eventHistoryTable.getColumn(eventHistoryTable.getRowByNumber(rowNumber), column).getText();
    }

    @Step("Получаем содержимое таблицы истории")
    public String getHistory() {
        return eventHistoryTable.getRowStream().map(SelenideElement::getText).collect(Collectors.joining());
    }

}
