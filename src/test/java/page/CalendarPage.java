package page;

import data.Currencies;
import elements.CalendarElements;
import io.qameta.allure.Step;

import java.util.Set;

public class CalendarPage extends BasePage<CalendarPage>{
   protected static class CalendarPageElements extends Elements implements CalendarElements {
   }
   protected CalendarPageElements elementHelper = new CalendarPageElements();

   @Step("Устанавливаем фильтр валют занчениями {0}")
   public CalendarPage  setCurrenciesFilter(Set<Currencies> currenciesSet) {
      return setFilterCheckboxGroup(elementHelper.getCurrenciesFilter(), currenciesSet);
   }
}
