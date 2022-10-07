package tests;


import com.codeborne.selenide.Selenide;
import config.TestConfiguration;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.apache.commons.configuration2.Configuration;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class BaseTest implements BeforeAllCallback, BeforeEachCallback, AfterEachCallback {
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
}
