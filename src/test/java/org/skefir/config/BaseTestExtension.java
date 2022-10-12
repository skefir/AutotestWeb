package org.skefir.config;


import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration2.Configuration;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

@Slf4j
public class BaseTestExtension implements BeforeAllCallback, BeforeEachCallback, AfterEachCallback {


    private static final Configuration CONFIG = TestConfiguration.getConfiguration();

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        if (CONFIG.getString("selenide.driverPath", null) == null) {
            ChromeDriverManager.getInstance().setup();
        }
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        Selenide.closeWebDriver();
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        openBrowser(CONFIG.getString("urls.metaUrl"));
    }

    @Step("Открываем страницу {0}")
    private static void openBrowser(String url) {
        log.info("Открываем страницу {}", url);
        Selenide.open(url);
    }

}

