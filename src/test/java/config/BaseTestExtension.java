package config;


import com.codeborne.selenide.Selenide;
import data.Currencies;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.apache.commons.configuration2.Configuration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import page.CalendarPage;

import java.util.EnumSet;
import java.util.Set;


public class BaseTestExtension implements BeforeAllCallback, BeforeEachCallback, AfterEachCallback {


    private static final Configuration CONFIG = TestConfiguration.getConfiguration();

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        if (CONFIG.getString("eleinide.driverPath", null) == null) {
            ChromeDriverManager.getInstance().setup();
        }
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        Selenide.closeWebDriver();
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        Selenide.open(CONFIG.getString("urls.metaUrl"));
    }

    private final CalendarPage calendarPage = new CalendarPage();

    @Test
    public void expTest() {
        Set<Currencies> currenciesSet = EnumSet.of(Currencies.CHF);
        calendarPage.setCurrenciesFilter(currenciesSet);
    }
}

