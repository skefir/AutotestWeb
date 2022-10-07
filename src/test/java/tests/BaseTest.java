package tests;


import config.TestConfiguration;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.apache.commons.configuration2.Configuration;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class BaseTest implements BeforeAllCallback {
    private static final Configuration CONFIG = TestConfiguration.getConfiguration();

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        if (CONFIG.getString("eleinide.driverPath", null) == null) {
            ChromeDriverManager.getInstance().setup();
        }
    }
}
