package config;

import com.codeborne.selenide.Configuration;
import lombok.SneakyThrows;
import lombok.Synchronized;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.lang3.StringUtils;

public class TestConfiguration {
    private static org.apache.commons.configuration2.Configuration instance;

    private TestConfiguration() {
    }

    private static void createConfig() {
        loadConfiguration();
        Configuration.timeout = instance.getInt("seleinide.timout", 10000);
        String browserName = instance.getString("seleinide.browser.name", null);
        if (StringUtils.isNotBlank(browserName)) {
            Configuration.browser = browserName;
        }
        String browserVersion = instance.getString("seleinide.browser.version", null);
        if (StringUtils.isNotBlank(browserVersion)) {
            Configuration.browserVersion = browserVersion;
        }
        String remoteUrl = instance.getString("selenide.remote.url", null);
        if (StringUtils.isNotBlank(remoteUrl)) {
            Configuration.remote = remoteUrl;
        }
        Configuration.browserSize = instance.getString("selenide.browser.size", "1920x1080");
    }

    @SneakyThrows
    @Synchronized
    private static void loadConfiguration() {
        if (instance == null) {
            FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                            .configure(new Parameters().properties()
                                    .setFileName(System.getProperty("autotest.config.file", "autotest.properties")));
            instance = builder.getConfiguration();
        }
    }

    public static org.apache.commons.configuration2.Configuration getConfiguration() {
        if (instance == null) {
            createConfig();
        }
        return instance;
    }
}
