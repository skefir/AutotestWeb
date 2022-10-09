package page;

import data.CalendarEventInfoTab;
import elements.CalendarEventInfoElements;
import elements.TabControl;
import io.qameta.allure.Step;

public class CalendarEventInfoPage extends BasePage<CalendarEventInfoPage> {
    protected static class CalendarEventInfoPageElements extends Elements implements CalendarEventInfoElements {
    }

    protected CalendarEventInfoPageElements elementHelper = new CalendarEventInfoPageElements();

    TabControl<CalendarEventInfoTab> tabControl = new TabControl<>(elementHelper.getEventTabControl());

    @Step("Переходим на вкладку {0}")
    public CalendarEventInfoPage goToTab(CalendarEventInfoTab tab) {
        tabControl.getTab(tab).click();
        return this;
    }

}
