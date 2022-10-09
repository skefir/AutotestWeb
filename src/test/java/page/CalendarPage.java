package page;

import data.Currencies;
import data.DateFilterOptions;
import data.ImportanceFilterOption;
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

   @Step("Устанавливаем фильтр дат занчением {0}")
   public CalendarPage  setDateFilter(DateFilterOptions dateFilterOptions) {
      return setFilterRadio(elementHelper.getDateFilter(), dateFilterOptions);
   }
   @Step("Устанавливаем фильтр важности занчениями {0}")
   public CalendarPage  setImportanceFilter(Set<ImportanceFilterOption> currenciesSet) {
      return setFilterCheckboxGroup(elementHelper.getImportanceFilter(), currenciesSet);
   }
}
