package tests;

import com.codeborne.selenide.junit5.ScreenShooterExtension;
import config.BaseTestExtension;
import data.Currencies;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import page.CalendarPage;

import java.util.EnumSet;
import java.util.Set;

@Slf4j
@ExtendWith({BaseTestExtension.class})
public class CalendarTests {

    @RegisterExtension
    static ScreenShooterExtension screenshotEmAll = new ScreenShooterExtension(true).to("target/screenshots");


    private final CalendarPage calendarPage = new CalendarPage();

    @Test
    public void expTest() {
        Set<Currencies> currenciesSet = EnumSet.of(Currencies.CHF);
        calendarPage.setCurrenciesFilter(currenciesSet);
    }
}
